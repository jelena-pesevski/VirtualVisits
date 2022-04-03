export class Log{

    info:string;
    action:string;
    dateTime: Date;

    constructor(info:string, action:string, dateTime:Date){
        this.info=info;
        this.action=action;
        this.dateTime=dateTime;
    }
}