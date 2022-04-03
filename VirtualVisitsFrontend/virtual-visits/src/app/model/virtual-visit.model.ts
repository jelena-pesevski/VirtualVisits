export class VirtualVisit{
    virtualVisitId:number | null;
    museumId:number | null;
    museumName:string | null;
    date:Date | null;
    start:Date | null;
    duration:Date | null;
    price:number | null;
    ytLink:string | null;

    constructor(virtualVisitId?:number, museumId?:number, museumName?:string, date?:Date, start?:Date, duration?:Date, price?:number, ytLink?:string){
        this.virtualVisitId=virtualVisitId || null;
        this.museumId=museumId || null;
        this.museumName=museumName || null;
        this.date=date || null;
        this.start=start || null;
        this.duration=duration || null;
        this.price=price || null;
        this.ytLink=ytLink || null;
    }
}