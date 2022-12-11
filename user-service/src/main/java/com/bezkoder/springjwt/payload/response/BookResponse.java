package com.bezkoder.springjwt.payload.response;

import java.util.Date;



public class BookResponse {
	
	private Long id;

	private String title;

	private Long authorId;
	
	private String authorName;

	private double price;

	private String category;

	private String content;

	private String publisher;

	private Date publisheddate;

	private boolean isBlocked;
	
	public BookResponse() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Long getAuthorId() {
		return authorId;
	}



	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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

	@Override
	public String toString() {
		return "BookResponse [id=" + id + ", title=" + title + ", authorId=" + authorId + ", authorName=" + authorName
				+ ", price=" + price + ", category=" + category + ", content=" + content + ", publisher=" + publisher
				+ ", publisheddate=" + publisheddate + ", isBlocked=" + isBlocked + "]";
	}
	
	

}
