import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LogRoutingModule } from './log-routing.module';
import { LogsContainerComponent } from './logs-container/logs-container.component';
import { AppMaterialModule } from '../app-material/app-material.module';


@NgModule({
  declarations: [
    LogsContainerComponent
  ],
  imports: [
    CommonModule,
    LogRoutingModule,
    AppMaterialModule
  ]
})
export class LogModule { }
