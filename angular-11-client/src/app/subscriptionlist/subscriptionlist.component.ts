import { Component, OnInit } from '@angular/core';
import { Book } from '../_models/book';
import { Subscription } from '../_models/subscription';
import { ReaderService } from '../_services/reader.service';

@Component({
  selector: 'app-subscriptionlist',
  templateUrl: './subscriptionlist.component.html',
  styleUrls: ['./subscriptionlist.component.css']
})
export class SubscriptionlistComponent implements OnInit {

  subscriptions: Subscription[];
  subscription: Subscription;
  books: Book[];
  constructor(private readerService: ReaderService) { }

  ngOnInit(): void {
    this.getSubscribedBooks();
    this.getSubscriptions();
  }

  private getSubscriptions() {
    this.readerService.getSubscriptions().subscribe(data => {
      this.subscriptions=data;
      console.log("SUBSCRIPTIONS:"+this.subscriptions);
    })
  }

  private getSubscribedBooks() {
    this.readerService.fectchAllSubscribedBook().subscribe(data=> {
      this.books=data;
    })
  }

  getSubscriptionOfBook(id:number){
    this.readerService.getSubscriptionOfBook(id).subscribe(data=> {
      this.subscription=data;
      console.log("SUBSCRIPTION:"+this.subscription);
      this.reloadPage();
    }, error=> {
      console.log(error);
    })
  }

  unsubscribe(id:number) {
    this.getSubscriptionOfBook(id);
    this.readerService.cancelSubscription(this.subscription.subId).subscribe(data=> {
      alert("Subscription cancelled");
      this.reloadPage();
    }, error=> {
      console.log(error);
    })
  }

  reloadPage(): void {
    window.location.reload();
  }
}
