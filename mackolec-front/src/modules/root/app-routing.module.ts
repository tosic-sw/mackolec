import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlarmNotificationsPageComponent } from './pages/alarm-notifications-page/alarm-notifications-page.component';
import { HospitalizedCatsPageComponent } from './pages/hospitalized-cats-page/hospitalized-cats-page.component';
import { NotFoundPageComponent } from './pages/not-found-page/not-found-page.component';
import { RootLayoutPageComponent } from './pages/root-layout-page/root-layout-page.component';
import { WelcomePageComponent } from './pages/welcome-page/welcome-page.component';

const routes: Routes = [
  {
    path: "my-app",
    component: RootLayoutPageComponent,
    children: [
      {
        path: "welcome",
        component: WelcomePageComponent,
      },
      {
        path: "notifications",
        component: AlarmNotificationsPageComponent,
      },
      {
        path: "hospitalized-cats",
        component: HospitalizedCatsPageComponent,
      }
    ]
  },
  {
    path: "",
    redirectTo: "my-app/welcome",
    pathMatch: "full",
  },
  {
    path: "**",
    component: NotFoundPageComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
