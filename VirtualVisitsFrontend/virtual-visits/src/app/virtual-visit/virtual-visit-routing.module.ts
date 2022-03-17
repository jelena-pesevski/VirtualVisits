import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActiveVisitContainerComponent } from './active-visit-container/active-visit-container.component';
import { ActiveVisitsContainerComponent } from './active-visits-container/active-visits-container.component';
import { VirtualVisitContainerComponent } from './virtual-visit-container/virtual-visit-container.component';

const routes: Routes = [{
  path:'',
  component:VirtualVisitContainerComponent,
  children:[
    {
      path:'',
      component:ActiveVisitsContainerComponent

    },
    {
      path:'attend',
      component:ActiveVisitContainerComponent
    }
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VirtualVisitRoutingModule { }
