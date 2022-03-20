import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { Root } from 'src/app/model/rssObjects.model';

import { RssService } from './rss.service';

describe('RssService', () => {
  let service: RssService;
  let httpClientSpy: jasmine.SpyObj<HttpClient>;

  beforeEach(() => {
    const httpSpy = jasmine.createSpyObj('HttpClient', ['get']);

    TestBed.configureTestingModule({
      providers: [RssService, 
        {
          provide: HttpClient, useValue:httpSpy
        }]
    });
    service = TestBed.inject(RssService);
    httpClientSpy=TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return rss feed in json format', (done:DoneFn)=>{
    const expectedRoot: Root={
      "status": "ok",
      "feed": {
      "url": "http://www.huffpost.com/section/arts/feed",
      "title": "Culture and Arts",
      "link": "https://www.huffpost.com/entertainment/arts",
      "author": "",
      "description": "Expect to be delighted and outraged by our incisive and sprawling coverage of culture and arts.",
      "image": ""
      },
      "items": [
        {
          "title": "How 'X' And 'Minx' Unpack The Politics Of Porn In The '70s",
          "pubDate": "2022-03-18 19:47:34",
          "link": "https://www.huffpost.com/entry/x-minx-70s-porn-politics_n_6234a9f2e4b046c938db4eae",
          "guid": "https://www.huffpost.com/entry/x-minx-70s-porn-politics_n_6234a9f2e4b046c938db4eae",
          "author": "",
          "thumbnail": "",
          "description": "The new slasher film and the HBO series center the perils and thrills of X-rated content  throughout the \"Me Decade.\"",
          "content": "The new slasher film and the HBO series center the perils and thrills of X-rated content  throughout the \"Me Decade.\"",
          "enclosure": {
          "link": "https://img.huffingtonpost.com/asset/6234aa051e0000b6311b092c.jpg?cache=7lpcl46ww0&amp;ops=224_126",
          "type": "image/jpeg"
        },
       "categories": []
      }]
    };

    httpClientSpy.get.and.returnValue(of(expectedRoot));

    service.reedRssFeed().subscribe({
      next:data=>{
        expect(data)
        .withContext('expected rss feed')
        .toEqual(expectedRoot);
        done();
      },
      error: done.fail
    })

    expect(httpClientSpy.get.calls.count())
    .withContext('one call')
    .toBe(1);
  })
});
