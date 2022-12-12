import { Component, OnInit } from '@angular/core';
import { Book } from '../_models/book';
import { AuthorService } from '../_services/author.service';

@Component({
  selector: 'app-booklist',
  templateUrl: './booklist.component.html',
  styleUrls: ['./booklist.component.css']
})
export class BooklistComponent implements OnInit {
  books: Book[];
  booklist:any={
    category:"",
    title:"",
    author:"",
    price:"",
    publisher:""

  };
  constructor( private authorService: AuthorService) { }

  ngOnInit(): void {
  //   this.books =[
  // {
  // "id": 1,

	// "title": "Jane Eyre",

	// "authorId": 4,
	
	// "price": 500,
	
	// "authorName": "jbjbjhbc",

	// "category": "romance",

	// "content": "content",

	// "publisher": "publisher",

	// "publisheddate": "hjgjg",

	// "isBlocked": true
  // }];
    this.getAllBooks();
  }

  private getAllBooks() {
    this.authorService.getAllBooks().subscribe(data=> {
      this.books=data;
    })
  }

}
