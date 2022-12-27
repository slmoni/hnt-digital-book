import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '../_models/book';

const API_URL = 'http://localhost:8081/digitalbooks/';
// const AUTH_API = 'http://35.78.102.161:8081/digitalbooks/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(API_URL + 'signin', {
      username,
      password
    }, httpOptions);
  }

  register(username: string, email: string, password: string, role:any): Observable<any> {
    return this.http.post(API_URL + 'signup', {
      username,
      email,
      password,
      role
    }, httpOptions);
  }

  search(category: any, title:any, author:any, price:any, publisher: any): Observable<any> {
    return this.http.get<Book>(API_URL+'search/'+category+"/"+title+"/"+author+"/"+price+"/"+publisher); 
  }
}
