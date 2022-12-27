import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';
import { Subscription } from '../_models/subscription';
import { BooklistComponent } from '../booklist/booklist.component';
import { Book } from '../_models/book';

const API_URL = 'http://localhost:8081/digitalbooks/';
// const API_URL = 'http://35.78.102.161:8081/digitalbooks/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class ReaderService {

  constructor(private http: HttpClient, private tokenService: TokenStorageService) { }

  user = this.tokenService.getUser();

    // /readers/{userId}/books/{subId}/cancel-subscription
  cancelSubscription(subId:any) : Observable<any> {
    return this.http.post(API_URL +"readers/"+this.user.id+"/books/"+subId+"/cancel-subscription",httpOptions);
  }

// /readers/{bookId}/subscribe
  subscribeBook(subscription: any, bookId:any) : Observable<any> {
    return this.http.post(API_URL +"readers/"+bookId+"/subscribe",subscription);
  }

  ///readers/{userId}/books
  fectchAllSubscribedBook() {
    return this.http.get<Book[]>(API_URL+"readers/"+this.user.id+"/books");
  }

  // @PostMapping("/readers/{userId}/books/{subId}")
  fetchSubscribedBook(subId:any) {
    return this.http.get<Book>(API_URL+"readers/"+this.user.id+"/books/"+subId);
  }

  // @GetMapping("/readers/subscriptions/{userId}")
  getSubscriptions() {
    return this.http.get<Subscription[]>(API_URL+"readers/subscriptions/"+this.user.id);
  }

  // @GetMapping("/readers/{userId}/subscription/book/{bookId}")
  getSubscriptionOfBook(bookId:any) {
    return this.http.get<Subscription>(API_URL+"readers/"+this.user.id+"/subscription/book/"+bookId);
  }
}
