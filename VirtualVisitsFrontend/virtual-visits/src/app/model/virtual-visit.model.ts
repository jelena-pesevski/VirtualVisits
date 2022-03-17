export class VirtualVisit{
    virtualVisitId:number;
    museumId:number;
    museumName:string;
    date:Date;
    start:Date;
    duration:Date;
    price:number;

    constructor(virtualVisitId:number, museumId:number, museumName:string, date:Date, start:Date, duration:Date, price:number){
        this.virtualVisitId=virtualVisitId;
        this.museumId=museumId;
        this.museumName=museumName;
        this.date=date;
        this.start=start;
        this.duration=duration;
        this.price=price;
    }
}