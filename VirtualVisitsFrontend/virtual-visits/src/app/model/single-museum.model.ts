export class MuseumDetails{
    museumId:number | null;
    name: string | null;
    address:string | null;
    phone: string | null;
    city: string | null;
    country: string | null;
    type:string | null;
    longitude:number | null;
    latitude:number | null;

    constructor(museumId?:number, name?:string, address?:string, phone?:string, city?:string, country?:string, type?:string, longitude?:number, latitude?:number){
        this.museumId=museumId || null;
        this.name=name || null;
        this.address=address || null;
        this.phone=phone || null;
        this.city=city || null;
        this.country=country || null;
        this.type=type || null;
        this.longitude=longitude || null;
        this.latitude=latitude || null;
    }
}