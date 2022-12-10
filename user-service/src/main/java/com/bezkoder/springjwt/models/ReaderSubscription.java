package com.bezkoder.springjwt.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="subscription")
public class ReaderSubscription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subId;
	
	@Column(name="bookId")
	private Long bookId;
	
	@Column(name="userId")
	@NotNull
	private long userId;
	
	@Column(name="isSubscribed")
	private boolean isSubscribed;
	
	@Column(name="dateOfSubscription")
	private Date dateOfSubscription;
	
	@Column(name="timeOfSubscription")
	private Timestamp timeOfSubscription;
	
	public ReaderSubscription() {
		
	}

	public ReaderSubscription(Long subId, Long bookId, long userId, boolean isSubscribed, Date dateOfSubscription,
			Timestamp timeOfSubscription) {
		super();
		this.subId = subId;
		this.bookId = bookId;
		this.userId = userId;
		this.isSubscribed = isSubscribed;
		this.dateOfSubscription = dateOfSubscription;
		this.timeOfSubscription = timeOfSubscription;
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean isSubscribed() {
		return isSubscribed;
	}

	public void setSubscribed(boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	public Date getDateOfSubscription() {
		return dateOfSubscription;
	}

	public void setDateOfSubscription(Date dateOfSubscription) {
		this.dateOfSubscription = dateOfSubscription;
	}

	public Timestamp getTimeOfSubscription() {
		return timeOfSubscription;
	}

	public void setTimeOfSubscription(Timestamp timeOfSubscription) {
		this.timeOfSubscription = timeOfSubscription;
	}

	@Override
	public String toString() {
		return "ReaderSubscription [subId=" + subId + ", bookId=" + bookId + ", userId=" + userId + ", isSubscribed="
				+ isSubscribed + ", dateOfSubscription=" + dateOfSubscription + ", timeOfSubscription="
				+ timeOfSubscription + "]";
	}
	
	
	
	
}
