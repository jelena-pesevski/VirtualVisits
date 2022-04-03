import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuardService } from '../auth/services/admin-guard.service';
import { MuseumContainerComponent } from './museum-container/museum-container.component';
import { MuseumDetailsContainerComponent } from './museum-details-container/museum-details-container.component';
import { MuseumListComponent } from './museum-list/museum-list.component';
import { MuseumsAdminComponent } from './museums-admin/museums-admin.component';

const routes: Routes = [{
  path:'',
  component:MuseumContainerComponent,
  children:[
    {
      path:'',
      component:MuseumListComponent
    },
    {
      path:'details/:museumId',
      component:MuseumDetailsContainerComponent
    },
    {
      path:'admin',
      component:MuseumsAdminComponent,
      canActivate:[AdminGuardService]
    }
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MuseumRoutingModule { }
