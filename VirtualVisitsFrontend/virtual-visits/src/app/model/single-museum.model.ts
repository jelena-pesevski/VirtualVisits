export class SingleMuseum{
    museumId:number;
    name: string;
    address:string;
    phone: string;
    city: string;
    country: string;
    type:string;
    longitude:number;
    latitude:number;

    constructor(museumId:number, name:string, address:string, phone:string, city:string, country:string, type:string, longitude:number, latitude:number){
        this.museumId=museumId;
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.city=city;
        this.country=country;
        this.type=type;
        this.longitude=longitude;
        this.latitude=latitude;
    }
}