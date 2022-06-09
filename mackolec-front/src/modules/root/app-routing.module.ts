import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
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
    ]
    //   {
    //     path: "admin",
    //     loadChildren: () =>
    //       import("./../admin/admin.module").then((m) => m.AdminModule),
    //   },
    //   {
    //     path: "user",
    //     loadChildren: () =>
    //       import("./../user/user.module").then((m) => m.UserModule),
    //   }
    // ]
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
