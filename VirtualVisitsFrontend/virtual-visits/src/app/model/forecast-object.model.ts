export class ForecastObject{

    name:string;
    temp:number;
    icon:string;

    constructor(name:string, temp:number, icon:string){
        this.name=name;
        this.temp=temp;
        this.icon=icon;
    }
}