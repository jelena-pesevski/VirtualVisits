export class Museum{
    museumId:number;
    name: string;
    city: string;

    constructor(museumId:number, name:string,  city:string){
        this.museumId=museumId;
        this.name=name;
        this.city=city;
    }
}