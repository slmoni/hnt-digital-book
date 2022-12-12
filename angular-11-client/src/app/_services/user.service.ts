import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8081/digitalbooks/test/';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user', { responseType: 'text' });
  }

  getReaderBoard(): Observable<any> {
    return this.http.get(API_URL + 'reader', { responseType: 'text' });
  }

  getAuthorBoard(): Observable<any> {
    return this.http.get(API_URL + 'author', { responseType: 'text' });
  }

}
