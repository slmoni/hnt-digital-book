package com.bezkoder.springjwt.security.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.bezkoder.springjwt.models.ReaderSubscription;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.SubscriptionRepository;
import com.bezkoder.springjwt.repository.UserRepository;

public class UserBookService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	SubscriptionRepository susbscriptionRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
//	public boolean findAuthorById(Long authorId) {
//		Optional<User> user = userRepo.findById(authorId);
//		if(!user.isEmpty()) {
//			if(user.get().getRoles()) {
//				return true;
//			}
//		}
//		return false;
//	}
	
	public ReaderSubscription getSubscription(Long userId, Long subsId) {
		
		ReaderSubscription subscription = (ReaderSubscription) getAllSubscriptions(userId)
				.stream()
				.filter(subs-> subs.getSubId().equals(subsId) && subs.isSubscribed())
				.findAny()
				.orElse(null);
		if(subscription!=null) {
			return subscription;
		}
		return null;
	}
	
	public Set<ReaderSubscription> getAllSubscriptions(Long userId) {		
		Set<ReaderSubscription> subsList= new HashSet<>();
		Optional<User> user = userRepo.findById(userId);
		if(user.isPresent() && user.get()!=null)
			subsList = user.get().getSubscriptions();
		return subsList;
	}
	

}
