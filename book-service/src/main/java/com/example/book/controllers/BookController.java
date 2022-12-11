package com.example.book.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.models.Book;
import com.example.book.models.BookContent;
import com.example.book.repositories.BookRepo;
import com.example.book.services.BookService;

@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private BookService bookService;
	
	/* Search books */
	@GetMapping("/search/{category}/{title}/{authorId}/{price}/{publisher}")
	public List<Book> searchbooks(@PathVariable String category, @PathVariable String title, @PathVariable int authorId, @PathVariable String publisher) {
		return bookService.searchBook(category, title, authorId, publisher);
	}
	
	/* Author create book */
	@PostMapping("/author/{authorId}/createbook")
	public ResponseEntity<?> createBook(@RequestBody @Valid Book book, @PathVariable int authorId) throws Exception {
		if(authorId == 0) {
			return ResponseEntity.badRequest().body("Please give valid author Id");
		}
		return new ResponseEntity<>( bookService.createBook(book, authorId), HttpStatus.OK);
	}
	
	/* Author update book */
	@PutMapping("/author/{authorId}/updatebook/{id}")
	public ResponseEntity<?> updateBook(@RequestBody @Valid Book book, @PathVariable int authorId, @PathVariable int id) throws Exception {
		return new ResponseEntity<>(bookService.updateBook(book, authorId, id), HttpStatus.OK);
	}
	
	/* Author block/unblock book */
	@PostMapping("/author/{authorId}/books/{id}/{isBlocked}")
	public  ResponseEntity<?> blockBook(@PathVariable int authorId, @PathVariable int id, @PathVariable String isBlocked) throws Exception {
		return new ResponseEntity<>( bookService.blockBook(authorId, id, isBlocked),HttpStatus.OK);
	}
	
	/* Reader can read a book */
	@GetMapping("books/{id}/readbook")
	public ResponseEntity<?> readBook(@PathVariable int id) {
		Book book= bookService.readBook(id);
		if(book!=null) {
			return ResponseEntity.ok(book);
		} else {
			return ResponseEntity.badRequest().body("Book does not exist"); 
		}
	}
	
	/* Reader get subscribed book */ //Needs to be updated
//	@GetMapping("/{id}/getsubscribedbook")
//	public ResponseEntity<?> getSubscribedBook(@PathVariable int id) {
//		Book subscribed = bookService.getSubscribedBook(id);
//		if(subscribed !=null) {
//			return ResponseEntity.ok(subscribed);
//		} else {
//			return ResponseEntity.badRequest().body("Book not susbcribed");
//		}
//		 
//	}
	
	
	/* Reader can get book by Id */
	@GetMapping("/getbook/{id}")
	public ResponseEntity<?> getBook(@PathVariable int id) {
		 Book book = bookService.getBook(id);
		 if(!(ObjectUtils.isEmpty(book)) && book.isBlocked()==false) {
			 return ResponseEntity.ok(book);
		 } else {
			 return ResponseEntity.badRequest().body("Book does not exist");
		 }
	}
	
	/* Reader get all subscribed books */
	@PostMapping("/getallsubscribedbook")
	public ResponseEntity<?> getAllSubscribedBook(@RequestBody List<Integer> bookId) {
		if(bookId.isEmpty()) {
			return ResponseEntity.badRequest().body("Invalid Ids");
		}
		List<Book> subscribedBooks= bookService.getAllSubscribedBooks(bookId);
		if(subscribedBooks.isEmpty()) {
			return ResponseEntity.badRequest().body("No books with the given Ids");
		}else {
			return ResponseEntity.ok(subscribedBooks);
		} 
	}

}
