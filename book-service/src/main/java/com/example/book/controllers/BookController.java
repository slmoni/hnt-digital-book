package com.example.book.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.models.Book;
import com.example.book.models.BookContent;
import com.example.book.services.BookService;

@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/search/{category}/{title}/{authorId}/{price}/{publisher}")
	public List<Book> searchbooks(@PathVariable String category, @PathVariable String title, @PathVariable int authorId, @PathVariable String publisher) {
		return bookService.searchbook(category, title, authorId, publisher);
	}
	
	@PostMapping("/author/{authorId}/createbook")
	public ResponseEntity<Book> createBook(@RequestBody @Valid Book book, @PathVariable int authorId) throws Exception {
		return new ResponseEntity<>( bookService.createBook(book, authorId), HttpStatus.OK);
	}
	
	@PutMapping("/author/{authorId}/updatebook/{id}")
	public Book updateBook(@RequestBody @Valid Book book, @PathVariable int authorId, @PathVariable int id) throws Exception {
		return bookService.updateBook(book, authorId, id);
	}
	
	@PostMapping("/author/{authorId}/books/{id}/{isBlocked}")
	public Book blockBook(@PathVariable int authorId, @PathVariable int id, @PathVariable String isBlocked) throws Exception {
		return bookService.blockBook(authorId, id, isBlocked);
	}
	
	@GetMapping("{id}")
	public Optional<Book> subscribeBook(@PathVariable int id) {
		return bookService.subscribeBook(id);
	}
	
	@GetMapping("/subscribe/{id}")
	public BookContent subscribeContent(@PathVariable int id) {
		return bookService.subscribeContent(id);
	}

}
