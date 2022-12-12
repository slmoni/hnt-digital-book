package com.example.book.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book.models.Book;
import com.example.book.models.BookContent;
import com.example.book.repositories.BookRepo;

@Service
public class BookService {
	
	@Autowired
	private BookRepo bookRepo;
	
	public Book getBook(int id) {
		Optional<Book> existbook= bookRepo.findById(id);
		if(existbook.isPresent()) {
			return existbook.get();
		}
		return null;
	}
	
	public List<Book> getAllBooks(int authorId){
		List<Book> books=bookRepo.findAll();
		return books;
	}
	
	public Book createBook(Book book, int authorId) throws Exception{
		Optional<Book> createbook= bookRepo.findByTitleAndAuthorId(book.getTitle(),authorId);
		if(createbook.isEmpty()) {
			book.setAuthorId(authorId);
			return bookRepo.save(book);
		}
		else {
			throw new Exception("Book already exists");
		}

	}
	
	public Book updateBook(Book book, int authorId, int id) throws Exception {
		if(bookRepo.existsById(id)) {
			Book bookexisted= getBook(id);
			bookexisted.setAuthorId(authorId);
			bookexisted.setCategory(book.getCategory());
			bookexisted.setContent(book.getContent());
			bookexisted.setPrice(book.getPrice());
			bookexisted.setPublisheddate(book.getPublisheddate());
			bookexisted.setPublisher(book.getPublisher());
			bookexisted.setTitle(book.getTitle());
			return bookRepo.save(bookexisted);
		}else{
		throw new Exception("Cannot find the book with ID:"+id);
		}
	}
	
	public Optional<Book> blockBook(int authorId, int id, String block) throws Exception {
		Optional<Book> blockbook=bookRepo.findByIdAndAuthorId(id, authorId);
		if(blockbook!=null) {
			if(block.equalsIgnoreCase("yes")) {
				blockbook.get().setBlocked(true);
				bookRepo.save(blockbook.get());
				return blockbook;
			} else if(block.equalsIgnoreCase("no")) {
				blockbook.get().setBlocked(false);
				bookRepo.save(blockbook.get());
				return blockbook;
			}
		} else if(blockbook==null){
			throw new Exception("Book with the authorId doesn't exist:"+ authorId);
		}
		return null;
	}
	
	public List<Book> searchBook(String category, String title, int authorId, String publisher){
	 List<Book> searchedbooks= bookRepo.search(category, title, authorId, publisher);
	 if(searchedbooks.isEmpty()) {
		 System.out.println("No Books present");
		 return null;
	 }
	 return searchedbooks;
	}
	
	public Book readBook(int id) {
		if(bookRepo.existsById(id)) {
			Book book=getBook(id);
			if(!book.isBlocked()) {
				return book;
			}
		}
		return null;
	}
	
//	public Book getSubscribedBook(int id) {
//		Book subscribebook = getBook(id);
//		if(subscribebook!=null) {
//			return subscribebook;
//		} else {
//			return null;
//		}
//	}
	
	
	public BookContent subscribeContent(int id) {
		Optional<Book> subscribebook = bookRepo.findById(id);
		BookContent content = new BookContent();
		if(!(subscribebook.isEmpty())) {
			content.setContent(subscribebook.get().getContent());
			content.setSubscribed(true);
			return content;
		} else {
			return null;
		}
	}
	
	
	public List<Book> getAllSubscribedBooks(List<Integer> bookIds) {
		List<Book> susbscribedBookList = new ArrayList<Book>();
		List<Book> bookList= bookRepo.findAllById(bookIds);
		susbscribedBookList=bookList.stream().filter(Book::isBlocked).collect(Collectors.toList());
		return susbscribedBookList;
	}
	
}


