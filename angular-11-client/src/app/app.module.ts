import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardAdminComponent } from './board-author/board-author.component';
import { BoardModeratorComponent } from './board-reader/board-reader.component';
import { BoardUserComponent } from './board-user/board-user.component';

import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { SearchbookComponent } from './searchbook/searchbook.component';
import { BooklistComponent } from './booklist/booklist.component';
import { CreatebookComponent } from './createbook/createbook.component';
import { UpdatebookComponent } from './updatebook/updatebook.component';
import { SubscriptionlistComponent } from './subscriptionlist/subscriptionlist.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardAdminComponent,
    BoardModeratorComponent,
    BoardUserComponent,
    SearchbookComponent,
    BooklistComponent,
    CreatebookComponent,
    UpdatebookComponent,
    SubscriptionlistComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
