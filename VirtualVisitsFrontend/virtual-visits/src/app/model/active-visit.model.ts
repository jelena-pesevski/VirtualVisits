export class ActiveVisit{

    imagesUrls:string[];
    endingTimeInMillis:number;
    videoUrl:string | null;
    ytLink:string | null;
  

    constructor(imagesUrls:string[], endingTimeInMillis:number,  videoUrl?:string, ytLink?:string){
        this.imagesUrls=imagesUrls;
        this.videoUrl=videoUrl || null;
        this.ytLink=ytLink || null;
        this.endingTimeInMillis=endingTimeInMillis;
    }
}