import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BuyTicketComponent } from './buy-ticket/buy-ticket.component';

const routes: Routes = [{
  path:'',
  component:BuyTicketComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TicketsRoutingModule { }
