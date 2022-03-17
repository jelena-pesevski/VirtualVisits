import { Component, OnInit } from '@angular/core';
import { Root } from 'src/app/model/rssObjects.model';
import { RssService } from '../services/rss.service';

@Component({
  selector: 'app-rss-feed',
  templateUrl: './rss-feed.component.html',
  styleUrls: ['./rss-feed.component.css']
})
export class RssFeedComponent implements OnInit {

  rssRoot:Root | null=null;

  constructor(private rssService:RssService) { }

  ngOnInit(): void {
    this.rssService.reedRssFeed().subscribe({
      next:data=>{
        this.rssRoot=data;
      },
      error:err=>{
        console.log("error while reading rss");
      }
    })
  }

}
