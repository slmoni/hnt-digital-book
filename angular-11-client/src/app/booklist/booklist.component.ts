import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Book } from '../_models/book';
import { AuthorService } from '../_services/author.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-booklist',
  templateUrl: './booklist.component.html',
  styleUrls: ['./booklist.component.css']
})
export class BooklistComponent implements OnInit {
  blockMessage="";
  errorMessage="";
  books: Book[];
  id:number
  book:Book = new Book();
  // booklist:any={
  //   category:"",
  //   title:"",
  //   author:"",
  //   price:"",
  //   publisher:""

  // };
  constructor( private authorService: AuthorService,
                private tokenService: TokenStorageService,
                private route: ActivatedRoute,
                private router: Router) { }

  user=this.tokenService.getUser();

  ngOnInit(): void { 
    // this.id=this.route.snapshot.params['id'];
    // console.log("BOOK ID:"+this.id);
    this.getAllBooks();
  }

  private getAllBooks() {
    this.authorService.getAllBooks(this.user.id).subscribe(data=> {
      this.books=data;
    })
  }

  updatebook(id:number) {
    this.router.navigate(['/updatebook', id]);
  }

  blockBook(id:number) {
      this.blockMessage="yes";
      this.authorService.blockBook(id, this.blockMessage).subscribe(data=> {
        this.book=data;
        alert('Book blocked');
        this.reloadPage();
      }, error=> {
        console.log(error);
        this.errorMessage=error.errorMessage;
      })
  }

  unblockBook(id:number) {
    this.blockMessage="no";
    this.authorService.blockBook(id, this.blockMessage).subscribe(data=> {
      this.book=data;
      alert('Book unblocked');
      this.reloadPage();
    }, error=> {
      console.log(error);
      this.errorMessage=error.errorMessage;
    })
  }

  reloadPage(): void {
    window.location.reload();
  }

}
