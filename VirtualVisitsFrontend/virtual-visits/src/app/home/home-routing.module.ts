import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../auth/login/login.component';
import { RegistrationComponent } from '../auth/registration/registration.component';
import { AdminGuardService } from '../auth/services/admin-guard.service';
import { MuseumDetailsContainerComponent } from '../museum/museum-details-container/museum-details-container.component';
import { HomeComponent } from './home/home.component';
import { RssFeedComponent } from './rss-feed/rss-feed.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    children:[
      {
        path:'',
        component:RssFeedComponent
      },
      {
        path:'museums',
        loadChildren:()=>import('../museum/museum.module').then(mod=>mod.MuseumModule),
      },
      {
        path:'virtual-visits',
        loadChildren:()=>import('../virtual-visit/virtual-visit.module').then(mod=>mod.VirtualVisitModule)
      },
      {
        path:'logs',
        loadChildren:()=>import('../log/log.module').then(mod=>mod.LogModule),
        canActivate: [AdminGuardService]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
