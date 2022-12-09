package com.example.book.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.book.models.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {
	
	@Query(value="select id, price, content, publisheddate, category, title, isBlocked, authorId, publisher from books b where b.category=:category and b.title=:title and b.authorId=:authorId and b.publisher=:publisher and b.isBlocked=false", nativeQuery = true)
	List<Book> search(String category,String title, int authorId, String publisher);
	
	Optional<Book> findByIdAndAuthorId(int id, int authorId);
	
	Optional<Book> findByTitleAndAuthorId(String title,int authorId);
	
}
