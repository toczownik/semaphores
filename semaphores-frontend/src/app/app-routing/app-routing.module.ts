import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {MapComponent} from "../map/map.component";
import {AdminComponent} from "../admin/admin.component";
import {HomeComponent} from "../home/home.component";

const routes: Routes = [
  {
    path: 'map',
    component: MapComponent
  },
  {
    path: 'admin',
    component: AdminComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  }
]


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
