package com.bezkoder.springjwt.payload.response;

import java.util.Date;



public class BookResponse {
	
	private int id;

	private String title;

	private int authorId;
	
	private String authorName;

	private double price;

	private String category;

	private String content;

	private String publisher;

	private Date publisheddate;

	private boolean isBlocked;
	
	public BookResponse() {
		
	}

	public BookResponse(int id, String title, int authorId, String authorName, double price, String category,
			String content, String publisher, Date publisheddate, boolean isBlocked) {
		super();
		this.id = id;
		this.title = title;
		this.authorId = authorId;
		this.authorName = authorName;
		this.price = price;
		this.category = category;
		this.content = content;
		this.publisher = publisher;
		this.publisheddate = publisheddate;
		this.isBlocked = isBlocked;
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
