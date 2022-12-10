package com.bezkoder.springjwt.payload.request;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class SubscriptionRequest {
	
	@NotNull
	private Long bookId;
	
	@NotNull
	private long userId;
	

	private boolean isSubscribed;
	

	private Date dateOfSubscription;
	

	private Timestamp timeOfSubscription;

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
		return "SubscriptionRequest [bookId=" + bookId + ", userId=" + userId + ", isSubscribed=" + isSubscribed
				+ ", dateOfSubscription=" + dateOfSubscription + ", timeOfSubscription=" + timeOfSubscription + "]";
	}
	
	
}
