import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../_models/book';
import { Subscription } from '../_models/subscription';
import { AuthService } from '../_services/auth.service';
import { ReaderService } from '../_services/reader.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-searchbook',
  templateUrl: './searchbook.component.html',
  styleUrls: ['./searchbook.component.css']
})
export class SearchbookComponent implements OnInit {

  isSuccessful=false;
  isSearchFailed=false;
  errorMessage="";
  content?: string;
  books:any={};
  search:any={
    category:"",
    title:"",
    author:"",
    price:"",
    publisher:""

  };
  private roles: string[] = [];
  isLoggedIn = false;
  showAuthorBoard = false;
  showReaderBoard = false;
  username?: string;
  subscription: any={};
  user:any;

  constructor(private authService: AuthService,
    private readerService: ReaderService,
    private tokenStorageService: TokenStorageService,
    private router:Router ) { }

  ngOnInit(): void {
      this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      this.user = this.tokenStorageService.getUser();
      this.roles = this.user.roles;
      this.username = this.user.username;
    }
  }

  searchBook(): void {
    // if(!(this.search.category)) {
    //   this.search.category ="";
    // }
    // if(!(this.search.title)) {
    //   this.search.title ="";
    // }
    // if(!(this.search.author)) {
    //   this.search.author ="";
    // }
    // if(!(this.search.price)) {
    //   this.search.price = null;
    // }
    // if(!(this.search.publisher)) {
    //   this.search.publisher ="";
    // }
    this.authService.search(this.search.category, this.search.title, this.search.author, this.search.price, this.search.publisher).subscribe(
      data => {
        this.books=data;
        console.log(data);
        this.isSuccessful = true;
      },
      err => {
        this.isSearchFailed=true;
        this.errorMessage = JSON.parse(err.error).message;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }


  subscribe(id: number) {
    if(("ROLE_AUTHOR"=== this.roles[0] ) || ("ROLE_READER"===this.roles[0])) {
      this.subscription = {
        bookId: id,
        userId: this.user.id,
      };

        this.readerService.subscribeBook(this.subscription, id).subscribe(data=> {
          this.isSuccessful=true;
          alert("Book Subscribed with ID:"+id);
          this.reloadPage();
        },error=> {
          this.isSuccessful=false;
          this.errorMessage= error;
          alert("Book cannot be subscribed:"+this.errorMessage)
        })        
    } else {
      this.router.navigate(['/login']).then(() => {
        this.reloadPage();
        });
    } 
  }

}
