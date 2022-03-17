import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Root } from 'src/app/model/rssObjects.model';

@Injectable({
  providedIn: 'root'
})
export class RssService {

  private rssFeedUrl:string="https://api.rss2json.com/v1/api.json?rss_url=http://www.huffpost.com/section/arts/feed";

  constructor(private http:HttpClient) { }

  reedRssFeed():Observable<Root>{
    return this.http.get<Root>(this.rssFeedUrl);
  }
}
