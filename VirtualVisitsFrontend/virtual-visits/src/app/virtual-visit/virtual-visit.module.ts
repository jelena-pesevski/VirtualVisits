import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VirtualVisitRoutingModule } from './virtual-visit-routing.module';
import { ActiveVisitsContainerComponent } from './active-visits-container/active-visits-container.component';
import { AppMaterialModule } from '../app-material/app-material.module';
import { VisitEntryComponent } from './visit-entry/visit-entry.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActiveVisitContainerComponent } from './active-visit-container/active-visit-container.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { VirtualVisitContainerComponent } from './virtual-visit-container/virtual-visit-container.component';


@NgModule({
  declarations: [
    ActiveVisitsContainerComponent,
    VisitEntryComponent,
    ActiveVisitContainerComponent,
    VirtualVisitContainerComponent,
  ],
  imports: [
    CommonModule,
    VirtualVisitRoutingModule,
    AppMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule
  ],
  exports:[
  ]
})
export class VirtualVisitModule { }
