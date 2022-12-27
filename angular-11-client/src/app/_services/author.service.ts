import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../_models/book';
import { TokenStorageService } from './token-storage.service';

const API_URL = 'http://localhost:8081/digitalbooks/';
// const API_URL = 'http://35.78.102.161:8081/digitalbooks/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  constructor(private http: HttpClient, private tokenService: TokenStorageService) { }

  user = this.tokenService.getUser();

  // ("/author/{authorId}/createbook")
  createBook(book: any) : Observable<any> {
    //let user = this.tokenService.getUser();
    return this.http.post(API_URL +"author/"+this.user.id+"/createbook",book);
  }
  // @PutMapping("/author/{authorId}/updatebook/{bookId}")
  updateBook( book: any) : Observable<any> {
    return this.http.put(API_URL +"author/"+this.user.id+"/updatebook/"+book.id,book);
  }

  getAllBooks(authorId:any) : Observable<Book[]> {
    return this.http.get<Book[]>(API_URL+"getAllBooksById/"+authorId);
  }

  getBookById(id:any): Observable<Book> {
    return this.http.get<Book>(API_URL+"getbook/"+id);
  }

  // ("/author/{authorId}/books/{bookId}/{isBlocked}")
  blockBook(bookId:number, isBlocked:any) : Observable <any> {
    return this.http.post(API_URL+"author/"+this.user.id+"/books/"+bookId+"/"+isBlocked,null);
  }
}
