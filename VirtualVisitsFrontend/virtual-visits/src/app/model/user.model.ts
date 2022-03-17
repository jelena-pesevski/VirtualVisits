export class User{
    userId:number ;
    firstname:string;
    lastname: string;
    username: string;
    mail: string;
    role: string;
    status: string;
    token: string;
    refreshToken:string ;
    otpToken: string | null;

    constructor(username:string, userId:number, firstname:string, lastname:string,  mail:string, role:string, status:string, token:string, refreshToken:string,  otpToken?:string){
        this.username=username;
        this.userId=userId;
        this.firstname= firstname;
        this.lastname= lastname;
        this.mail= mail;
        this.role=role;
        this.status=status;
        this.otpToken=otpToken || null;
        this.token= token;
        this.refreshToken=refreshToken;  
    }
}