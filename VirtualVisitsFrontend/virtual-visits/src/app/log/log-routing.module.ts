import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogsContainerComponent } from './logs-container/logs-container.component';

const routes: Routes = [
  {
    path: '',
    component: LogsContainerComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LogRoutingModule { }
