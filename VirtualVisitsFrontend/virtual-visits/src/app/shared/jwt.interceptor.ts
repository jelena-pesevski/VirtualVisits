import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, catchError, filter, Observable, switchMap, take, throwError } from "rxjs";
import { environment } from "src/environments/environment";
import { LoginService } from "../auth/services/login.service";
import { RefreshTokenService } from "./services/refresh-token.service";
import { TokenStorageService } from "./services/token-storage.service";


@Injectable()
export class JwtInterceptor implements HttpInterceptor{
    
    private isRefreshing=false;
    //we use BehaviourSubject ->
    //Same data get shared between all observers.
    //You can initialize the observable with default value 
    private refreshTokenSubject: BehaviorSubject<any>=new BehaviorSubject<any>(null);
    
    constructor(private loginService:LoginService, private tokenStorageService:TokenStorageService, private refreshTokenService:RefreshTokenService){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        //adding auth header if user is logged in and request is to the api url
        let authReq=req;
        const token=this.tokenStorageService.getToken();
        const isLoggedIn=this.loginService.isLoggedIn();
        const isApiUrl=req.url.startsWith(environment.BASE_URL);
        if(token && isApiUrl && isLoggedIn){
            authReq=this.addTokenHeader(req, token);
        }
        return next.handle(authReq).pipe(catchError((error:any)=>{
            if (error instanceof HttpErrorResponse && error.status === 403 && !authReq.url.includes('/login') && !authReq.url.includes("/refresh-token")  && !authReq.url.includes("/sign-up") && !authReq.url.includes("/logout")) {
                console.log("jwt expired");
                return this.handle403Error(authReq, next);
            }
            return throwError(() => error);
        }));
    }

    private addTokenHeader(request: HttpRequest<any>, token: string) {
        return request.clone({ headers: request.headers.set(environment.TOKEN_HEADER_KEY,'Bearer '+ token) });
    }

    private handle403Error(req:HttpRequest<any>, next:HttpHandler){
        //if refreshing is not in progress
        if(!this.isRefreshing){
            this.isRefreshing=true;
            this.refreshTokenSubject.next(null);
            const token=this.tokenStorageService.getRefreshToken();
            if(token){
                //The async pipe subscribes to an Observable or Promise and returns the latest value it has emitted
                return this.refreshTokenService.refreshToken(token).pipe(
                    //On each emission the previous inner observable (the result of the function you supplied) is cancelled and the new observable is subscribed
                    switchMap((newToken:any)=>{
                        this.isRefreshing=false;
                        this.tokenStorageService.saveToken(newToken.token);
                        this.refreshTokenSubject.next(newToken.token);
                    
                        return next.handle(this.addTokenHeader(req, newToken.token));
                    }),
                    catchError((err)=>{
                        this.isRefreshing=false;
                        this.loginService.logout();
                        //should navigate in each component
                        return throwError(() => err);
                    })
                )
            }
        }

        //refreshing is done, we take token and place it in header
        return this.refreshTokenSubject.pipe(
            filter(token => token !== null),
            take(1),
            switchMap((token) => next.handle(this.addTokenHeader(req, token)))
          );
    }
}