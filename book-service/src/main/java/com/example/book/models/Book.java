package com.example.book.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="books")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotBlank
	@Size(max=50)
	@Column(name="title")
	private String title;
	
	@NotNull(message="authorId should not be null")
	@Column(name="authorId")
	private int authorId;
	
	@Column(name="price")
	private double price;
	
	@Column(name="category")
	private String category;
	
	@Column(name="content")
	private String content;
	
	@Column(name="publisher")
	private String publisher;
	
	@Column(name="publisheddate")
	private Date publisheddate;
	
	@Column(name="isBlocked")
	private boolean isBlocked;
	
//	private BookContent bookContent;
//	@Column(name="isSubscribed")
//	private boolean isSubscribed;
	public Book() {
		
	}
	public Book(int id, String title, int authorId, double price, String category, String content, String publisher,
			Date publisheddate, boolean isBlocked, boolean isSubscribed) {
		super();
		this.id = id;
		this.title = title;
		this.authorId = authorId;
		this.price = price;
		this.category = category;
		this.content = content;
		this.publisher = publisher;
		this.publisheddate = publisheddate;
		this.isBlocked = isBlocked;
		//this.isSubscribed = isSubscribed;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getPublisheddate() {
		return publisheddate;
	}
	public void setPublisheddate(Date publisheddate) {
		this.publisheddate = publisheddate;
	}
	public boolean isBlocked() {
		return isBlocked;
	}
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
//	public BookContent getBookContent() {
//		return bookContent;
//	}
//	public void setBookContent(BookContent bookContent) {
//		this.bookContent = bookContent;
//	}
//	public boolean isSubscribed() {
//		return isSubscribed;
//	}
//	public void setSubscribed(boolean isSubscribed) {
//		this.isSubscribed = isSubscribed;
//	}
	
	

}
