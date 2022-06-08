import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SharedModule } from '../shared/shared.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NotFoundPageComponent } from './pages/not-found-page/not-found-page.component';
import { RootLayoutPageComponent } from './pages/root-layout-page/root-layout-page.component';
import { WelcomePageComponent } from './pages/welcome-page/welcome-page.component';
import { VetHeaderComponent } from './components/vet-header/vet-header.component';
import { AlarmNotificationsPageComponent } from './pages/alarm-notifications-page/alarm-notifications-page.component';
import { MatSelectModule } from '@angular/material/select';
import { SelectCustomTextComponent } from './components/select-custom-text/select-custom-text.component';
import { PaginationComponent } from './components/pagination/pagination.component';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldControl, MatFormFieldModule } from '@angular/material/form-field';
import { HospitalizedCatsPageComponent } from './pages/hospitalized-cats-page/hospitalized-cats-page.component';
import { SearchInputBtnComponent } from './components/search-input-btn/search-input-btn.component';

@NgModule({
  declarations: [
    AppComponent,
    RootLayoutPageComponent,
    NotFoundPageComponent,
    WelcomePageComponent,
    VetHeaderComponent,
    AlarmNotificationsPageComponent,
    SelectCustomTextComponent,
    PaginationComponent,
    HospitalizedCatsPageComponent,
    SearchInputBtnComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    SharedModule,
    FormsModule,
    MatSelectModule,
    MatInputModule,
    MatFormFieldModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
