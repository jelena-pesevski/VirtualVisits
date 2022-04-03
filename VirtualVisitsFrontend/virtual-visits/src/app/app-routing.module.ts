import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuardService } from './auth/services/admin-guard.service';
import { GuardService } from './auth/services/guard.service';

const routes: Routes = [
  {
    path:'museums',
    loadChildren:()=>import('./museum/museum.module').then(mod=>mod.MuseumModule),
    canActivate: [GuardService]
  },
  {
    path:'buy-ticket',
    loadChildren:()=>import('./tickets/tickets.module').then(mod=>mod.TicketsModule),
    canActivate: [GuardService]
  },
  {
    path:'virtual-visits',
    loadChildren:()=>import('./virtual-visit/virtual-visit.module').then(mod=>mod.VirtualVisitModule),
    canActivate: [GuardService]
  },
  {
    path: 'home',
    loadChildren: () => import('./home/home.module').then(mod => mod.HomeModule),
    canActivate: [GuardService]
  },
  {
    path:'',
    loadChildren: () => import('./auth/auth.module').then(mod => mod.AuthModule)
  },
  {
    path: '**',
    redirectTo: '',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
