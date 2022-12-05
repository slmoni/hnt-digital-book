package com.example.book.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book.models.Book;
import com.example.book.models.BookContent;
import com.example.book.repositories.BookRepo;

@Service
public class BookService {
	
	@Autowired
	private BookRepo bookRepo;
	
	public Book createBook(Book book, int authorId){
		//Book createbook= bookRepo.findByAuthorId(authorId);
			return bookRepo.save(book);

	}
	
	public Book updateBook(Book book, int authorId, int id) throws Exception {
		if(bookRepo.existsById(id)) {
			book.setAuthorId(authorId);
			return bookRepo.save(book);
		}else{
		throw new Exception("Cannot find the book with authorID:"+authorId);
		}
	}
	
	public Book blockBook(int authorId, int id, String block) throws Exception {
		Book blockbook=bookRepo.findByIdAndAuthorId(id, authorId);
		if(blockbook!=null) {
			if(block.equalsIgnoreCase("yes")) {
				blockbook.setBlocked(true);
				bookRepo.save(blockbook);
				return blockbook;
			} else if(block.equalsIgnoreCase("no")) {
				blockbook.setBlocked(false);
				return blockbook;
			}
		} else if(blockbook==null){
			throw new Exception("Book with the authorId doesn't exist:"+ authorId);
		}
		return null;
	}
	
	public List<Book> searchbook(String category, String title, int authorId, String publisher){
	 List<Book> searchedbooks= bookRepo.search(category, title, authorId, publisher);
	 if(searchedbooks.isEmpty()) {
		 System.out.println("No Books present");
		 return null;
	 }
	 return searchedbooks;
	}
	
	public Optional<Book> subscribeBook(int id) {
		Optional<Book> subscribebook = bookRepo.findById(id);
		if(bookRepo.existsById(id)) {
			
			//subscribebook.get().getBookContent().setSubscribed(true);
			return subscribebook;
		} else {
			return null;
		}
	}
	
	
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

}
