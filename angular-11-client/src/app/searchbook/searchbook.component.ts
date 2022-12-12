import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';

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

  constructor(private authService: AuthService ) { }
  
  search:any={
    category:"",
    title:"",
    author:"",
    price:"",
    publisher:""

  };

  ngOnInit(): void {
  }

  searchBook(): void {
    this.authService.search(this.search.category, this.search.title, this.search.author, this.search.price, this.search.publisher).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.content=data.Books;
      },
      err => {
        this.isSearchFailed=true;
        this.errorMessage = JSON.parse(err.error).message;
      }
    );
  }

}
