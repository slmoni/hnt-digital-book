import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from '../_models/book';
import { AuthorService } from '../_services/author.service';

@Component({
  selector: 'app-updatebook',
  templateUrl: './updatebook.component.html',
  styleUrls: ['./updatebook.component.css']
})
export class UpdatebookComponent implements OnInit {

  isSuccessful=false;
  errorMessage ="";
  id:number;
  book:Book = new Book();
  constructor(private authorService:AuthorService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.id=this.route.snapshot.params['id'];
    this.authorService.getBookById(this.id).subscribe(data => {
      this.book=data;
    }, error=> console.log(error));
  }

  goToBookList() {
    this.router.navigate(['/booklist']);
  }

  updateBook() {
    this.authorService.updateBook(this.book).subscribe(data=> {
      this.isSuccessful=true;
      this.goToBookList();
    }, error=> {
      console.log(error);
      this.errorMessage=error.error;
      this.isSuccessful=false
    })
  }
  
  onSubmit() {
    this.updateBook();
  }



}
