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
import { SymptomsComponent } from './components/symptoms/symptoms.component';
import { MaterialExampleModule } from 'src/material.module';
import { GeneralAboutCatComponent } from './components/general-about-cat/general-about-cat.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    RootLayoutPageComponent,
    NotFoundPageComponent,
    WelcomePageComponent,
    VetHeaderComponent,
    SymptomsComponent,
    GeneralAboutCatComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    SharedModule,
    MaterialExampleModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
