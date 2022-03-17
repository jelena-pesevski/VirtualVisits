export class Root{
    status:string;
    feed:Feed;
    items:Item[];

    constructor(status:string, feed:Feed, items:Item[]){
        this.status=status;
        this.feed=feed;
        this.items=items;
    }
}

export class Feed{
    url:string;
    title:string;
    link:string;
    author:string;
    description:string;
    image:string;

    constructor(url:string, title:string, link:string, author:string, description:string, image:string){
        this.url=url;
        this.title=title;
        this.link=link;
        this.author=author;
        this.description=description;
        this.image=image;
    }
}

export class Item{
    title:string;
    pubDate:string;
    link: string;
    guid: string;
    author: string;
    thumbnail: string;
    description: string;
    content: string;
    enclosure: Enclosure;
    categories: any[];

    constructor(title:string, pubDate:string, link:string, guid:string, author:string, thumbnail:string, description:string,
        content:string, enclosure:Enclosure, categories:any[]){
            this.title=title;
            this.pubDate=pubDate;
            this.link=link;
            this.guid=guid;
            this.author=author;
            this.thumbnail=thumbnail;
            this.description=description;
            this.content=content;
            this.enclosure=enclosure;
            this.categories=categories;
        }
}

export class Enclosure{
    link:string;
    type:string;

    constructor(link:string, type:string){
        this.link=link;
        this.type=type;
    }

}