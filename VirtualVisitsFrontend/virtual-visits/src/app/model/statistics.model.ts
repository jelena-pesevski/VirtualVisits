export interface Statistics{
    numOfLoggedIn:number;
    numOfRegistrated:number;
    activeByHour:Map<string, number>;
}