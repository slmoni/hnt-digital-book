import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { BoardModeratorComponent } from './board-reader/board-reader.component';
import { BoardAdminComponent } from './board-author/board-author.component';
import { SearchbookComponent } from './searchbook/searchbook.component';
import { BooklistComponent } from './booklist/booklist.component';
import { CreatebookComponent } from './createbook/createbook.component';
import { UpdatebookComponent } from './updatebook/updatebook.component';
import { SubscriptionlistComponent } from './subscriptionlist/subscriptionlist.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: BoardUserComponent },
  { path: 'reader', component: BoardModeratorComponent },
  { path: 'author', component: BoardAdminComponent },
  { path: 'search', component: SearchbookComponent},
  { path: 'booklist', component: BooklistComponent},
  { path: 'createbook', component: CreatebookComponent},
  { path: 'updatebook/:id', component: UpdatebookComponent},
  { path: 'subscriptionlist', component: SubscriptionlistComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
