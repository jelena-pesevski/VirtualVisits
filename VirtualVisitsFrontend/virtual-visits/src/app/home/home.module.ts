import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home/home.component';
import { FlexLayoutModule } from "@angular/flex-layout";
import { AppMaterialModule } from '../app-material/app-material.module';
import { AuthModule } from '../auth/auth.module';
import { MuseumModule } from '../museum/museum.module';
import { VirtualVisitModule } from '../virtual-visit/virtual-visit.module';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RssFeedComponent } from './rss-feed/rss-feed.component';
import { StatisticsComponent } from './statistics/statistics.component';


@NgModule({
  declarations: [
    HomeComponent,
    NavBarComponent,
    RssFeedComponent,
    StatisticsComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    FlexLayoutModule,
    AppMaterialModule,
    AuthModule,
    MuseumModule,
    VirtualVisitModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class HomeModule { }
