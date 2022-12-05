package com.example.book.models;

import javax.validation.constraints.NotNull;

//@Entity
//@Table(name="bookcontent")
public class BookContent {
//	@Id
	private int bookId;
//	@Column
	@NotNull(message="content can't be empty")
	private String content;
//	@Column
	private boolean isSubscribed;
	private int readerId;
	
	public BookContent() {
		// TODO Auto-generated constructor stub
	}


	public BookContent(int bookId, String content, boolean isSubscribed, int readerId) {
		super();
		this.bookId = bookId;
		this.content = content;
		this.isSubscribed = isSubscribed;
		this.readerId = readerId;
	}
	
	public int getReaderId() {
		return readerId;
	}


	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isSubscribed() {
		return isSubscribed;
	}

	public void setSubscribed(boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}
	
}
