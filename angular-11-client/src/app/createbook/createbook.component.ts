import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../_models/book';
import { AuthorService } from '../_services/author.service';

@Component({
  selector: 'app-createbook',
  templateUrl: './createbook.component.html',
  styleUrls: ['./createbook.component.css']
})
export class CreatebookComponent implements OnInit {

  isSuccessful=false;
  errorMessage ="";
  book: Book = new Book()
  constructor( private authorservice: AuthorService,
                private router: Router) { }

  ngOnInit(): void {
  }

  saveBook() {
    this.authorservice.createBook(this.book).subscribe(data => {
      console.log(this.book);
      this.isSuccessful=true;
      this.goToBookList();
    },
    error => {
      console.log(error)
      this.errorMessage=error.error;
      this.isSuccessful=false
    });
  }

  goToBookList() {
    this.router.navigate(['/booklist']);
  }

  onSubmit(){
    console.log(this.book);
    this.saveBook();
  }

}
