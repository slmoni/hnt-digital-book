package com.bezkoder.springjwt.security.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.bezkoder.springjwt.models.ReaderSubscription;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.BookRequest;
import com.bezkoder.springjwt.payload.request.SubscriptionRequest;
import com.bezkoder.springjwt.payload.response.BookResponse;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.SubscriptionRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class UserBookService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	SubscriptionRepository susbscriptionRepo;
	
	@Autowired
	JwtUtils jwtUtils;
	
	public static final String bookServiceUrl="http://localhost:8082/api";

	
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
		if(user.isPresent() && user.get()!=null) {
			Set<ReaderSubscription> subs= user.get().getSubscriptions();
			//Set<ReaderSubscription> subsFromRepo = susbscriptionRepo.findAllById(null);
			// bookList.stream().filter(book-> (book.isBlocked()==false)).collect(Collectors.toList());
			subsList= subs.stream().filter(sub->sub.isSubscribed()).collect(Collectors.toSet());
			return subsList;
		}

		return null;
	}
	
	public ResponseEntity<?> getBook(int bookId) {
		if(ObjectUtils.isEmpty(bookId)) {
			return ResponseEntity.badRequest().body("Please give valid book Id");
		}
		// "/getbook/{bookId}"
		String url=bookServiceUrl+"/getbook/"+bookId;
		RestTemplate restTemplate= new RestTemplate();
		BookResponse book = restTemplate.getForObject(url, BookResponse.class);
		if(book== null || ObjectUtils.isEmpty(book)) {
			return ResponseEntity.badRequest().body("No Such books exist");
		}
			return ResponseEntity.ok(book);
		
	}
	
	
	public ResponseEntity<?> getAllBooksById(int authorId) {
		if(ObjectUtils.isEmpty(authorId)) {
			return ResponseEntity.badRequest().body("Please give valid author Id");
		}
		String url=bookServiceUrl+"/getAllBooksById/"+authorId;
		RestTemplate restTemplate= new RestTemplate();
		ResponseEntity<?> result = restTemplate.getForEntity(url, List.class);
			return ResponseEntity.ok(result.getBody());
		
	}
	
	public ResponseEntity<?> getAllBooks(){
		String url=bookServiceUrl+"/getAllBooks";
		RestTemplate restTemplate= new RestTemplate();
		ResponseEntity<?> result = restTemplate.getForEntity(url, List.class);
			return ResponseEntity.ok(result.getBody());
		
	}
	
	
	public ResponseEntity<?> createBook(HttpServletRequest request, @Valid @RequestBody BookRequest book, @PathVariable Long authorId) throws JsonParseException, JsonMappingException, IOException{
		if(ObjectUtils.isEmpty(authorId)) {
			return ResponseEntity.badRequest().body("Please give valid author Id");
		}
		String jwt = jwtUtils.parseJwt(request);
		if(jwt!=null && jwtUtils.validateJwtToken(jwt)) {
			String author=jwtUtils.getUserNameFromJwtToken(jwt);
			book.setAuthorName(author);
			book.setAuthorId(authorId);
		} else {
			return ResponseEntity.badRequest().body("Failed to validate jwt");
		}
		// /author/{authorId}/createbook"
		String url=bookServiceUrl+"/author/"+authorId+"/createbook";
		RestTemplate restTemplate= new RestTemplate();
		BookResponse response= restTemplate.postForObject(url, book, BookResponse.class);
		response.setAuthorName(book.getAuthorName());
		return ResponseEntity.ok(response);
	}
	
	
	public ResponseEntity<?> updatebook(@RequestBody BookRequest book, @PathVariable Long authorId, @PathVariable Long bookId) {
		if(ObjectUtils.isEmpty(authorId))
			return ResponseEntity.badRequest().body(new MessageResponse("Enter authorId"));
		if(ObjectUtils.isEmpty(bookId))
			return ResponseEntity.badRequest().body(new MessageResponse("Enter bookId"));
		// /author/{authorId}/updatebook/{id}
		String url = bookServiceUrl+"/author/"+authorId+"/updatebook/"+bookId;
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.put(url, book);
			return ResponseEntity.ok(book);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return ResponseEntity.badRequest().body("Something went wrong");
	}
	
	
	public ResponseEntity<?> blockBook(@PathVariable Long authorId, @PathVariable Long bookId, @PathVariable String isBlocked) {
		System.out.println("Block book called");
		if(ObjectUtils.isEmpty(authorId))
			return ResponseEntity.badRequest().body(new MessageResponse("Enter authorId"));
		if(ObjectUtils.isEmpty(bookId))
			return ResponseEntity.badRequest().body(new MessageResponse("Enter bookId"));
		// /author/{authorId}/books/{id}/{isBlocked}
		String url = bookServiceUrl+"/author/"+authorId+"/books/"+bookId+"/"+isBlocked;
		RestTemplate restTemplate = new RestTemplate();
		BookResponse response = restTemplate.postForObject(url, null, BookResponse.class);
		return ResponseEntity.ok(response);
	}
	
	
	public ResponseEntity<?>searchBooks(@PathVariable(required=false) String category, @PathVariable(required=false) String title, 
			@PathVariable(required=false) String author, @PathVariable(required=false) Long price, @PathVariable(required=false) String publisher) {
//		if(ObjectUtils.isEmpty(category))
//			return ResponseEntity.badRequest().body(new MessageResponse("Enter category"));
//		if(ObjectUtils.isEmpty(title))
//			return ResponseEntity.badRequest().body(new MessageResponse("Enter title"));
//		if(ObjectUtils.isEmpty(author))
//			return ResponseEntity.badRequest().body(new MessageResponse("Enter author name"));
//		if(ObjectUtils.isEmpty(publisher))
//			return ResponseEntity.badRequest().body(new MessageResponse("Enter publisher"));
//		if(price < 0)
//			return ResponseEntity.badRequest().body(new MessageResponse("Enter valid price"));
		Long authorId=null;
		if(!(ObjectUtils.isEmpty(author))) {
			Optional<User> user = userRepo.findByUsername(author);
			if(!(ObjectUtils.isEmpty(user))) {
				authorId=user.get().getId();
			} else {
				authorId=null;
				return ResponseEntity.badRequest().body(new MessageResponse("Enter valid author name"));
			}
		}
		// /search/{category}/{title}/{authorId}/{price}/{publisher}
		String url = bookServiceUrl +"/search/"+category+"/"+ title+"/"+authorId+"/"+price+"/"+publisher;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<?> result = restTemplate.getForEntity(url, List.class);
		if(result==null)
			return ResponseEntity.badRequest().body(new MessageResponse("No such books exist"));
		return ResponseEntity.ok(result.getBody());
	}
	
	
	public ResponseEntity<?> subscribeBook(@RequestBody SubscriptionRequest subscription, @PathVariable Long bookId) {
		if(ObjectUtils.isEmpty(bookId))
			return ResponseEntity.badRequest().body(new MessageResponse("Enter book Id"));
		// /getbook/{id}
		String url = bookServiceUrl +"/getbook/"+bookId;
		RestTemplate restTemplate = new RestTemplate();
		BookResponse book = restTemplate.getForObject(url, BookResponse.class);
		if((ObjectUtils.isEmpty(book)) || book.isBlocked()==true) {
			return ResponseEntity.badRequest().body(new MessageResponse("Enter valid book Id"));
		}
		
		Optional<User> user = userRepo.findById(subscription.getUserId());
		if(!(ObjectUtils.isEmpty(user))) {
			Optional<ReaderSubscription> isSubscribedAlready = susbscriptionRepo.findByBookIdAndUserId(bookId, user.get().getId());
			System.out.println("SUBSCRIPTIONS:"+isSubscribedAlready);
			if(ObjectUtils.isEmpty(isSubscribedAlready)) {
				ReaderSubscription subscribebook = new ReaderSubscription();
				subscribebook.setBookId(bookId);
				subscribebook.setUserId(user.get().getId());
				subscribebook.setDateOfSubscription(new Date());
	 		   	subscribebook.setTimeOfSubscription(new Timestamp(System.currentTimeMillis()));
	 		   	subscribebook.setSubscribed(true);
	 		   ReaderSubscription subscribed = susbscriptionRepo.save(subscribebook);
	 		   Set<ReaderSubscription> subscriptions= user.get().getSubscriptions();
	 		   subscriptions.add(subscribed);
	 		   user.get().setSubscriptions(subscriptions);
	 		   userRepo.save(user.get());
	 		  return ResponseEntity.ok("Book Subscribed");
			}
			 return ResponseEntity.badRequest().body(new MessageResponse("Book is already subscribed"));
		}
		return ResponseEntity.badRequest().body(new MessageResponse("User does not exist"));	
	}
	
	
	public ResponseEntity<?>cancelSubscription(@PathVariable Long userId, @PathVariable Long subId) {
		if(ObjectUtils.isEmpty(userId))
			return ResponseEntity.badRequest().body(new MessageResponse("Enter user Id"));
		if(ObjectUtils.isEmpty(subId))
			return ResponseEntity.badRequest().body(new MessageResponse("Enter subscription Id"));
		if(userRepo.findById(userId).isEmpty())
			return ResponseEntity.badRequest().body(new MessageResponse("Enter valid user Id"));

		ReaderSubscription subscription = getSubscription(userId, subId);
		if (!(ObjectUtils.isEmpty(subscription))) {
			int HOURS_24 = 24 * 60 * 60 * 1000;
			if (System.currentTimeMillis() - subscription.getTimeOfSubscription().getTime() > HOURS_24) 
				return ResponseEntity.badRequest().body(new MessageResponse("Cancellation time expired"));
				
			subscription.setSubscribed(false);
			susbscriptionRepo.save(subscription);
			return ResponseEntity.ok(new MessageResponse("Subscription cancelled"));
		}
		return ResponseEntity.badRequest().body(new MessageResponse("User not subscribed"));
	}
	
	
	public ResponseEntity<?> fetchSubscribedBook(@PathVariable Long userId, @PathVariable Long subId) {
		if(ObjectUtils.isEmpty(userId))
			return ResponseEntity.badRequest().body(new MessageResponse("Enter user id"));
		if(ObjectUtils.isEmpty(subId))
			return ResponseEntity.badRequest().body(new MessageResponse("Enter subscription id"));
		if(userRepo.findById(userId).isEmpty())
			return ResponseEntity.badRequest().body(new MessageResponse("Enter valid user Id"));
		ReaderSubscription subscription = getSubscription(userId, subId);
		Long bookId=subscription.getBookId();
		// /getbook/{id}
		String url = bookServiceUrl +"/getbook/"+bookId;
		RestTemplate restTemplate = new RestTemplate();
		BookResponse response = restTemplate.getForObject(url, BookResponse.class);
		
		return ResponseEntity.ok(response);
	}
	
	
	public ResponseEntity<?>fectchAllSubscribedBook(@PathVariable Long userId) {
		if(ObjectUtils.isEmpty(userId))
			return ResponseEntity.badRequest().body(new MessageResponse("Enter user Id"));
		if(userRepo.findById(userId).isEmpty())
			return ResponseEntity.badRequest().body(new MessageResponse("Enter valid user Id"));
		
		Set<ReaderSubscription> subscriptionsList = getAllSubscriptions(userId);
		if(!subscriptionsList.isEmpty() && !(subscriptionsList == null)) {
		List<Long> bookIdList = subscriptionsList.stream().map(subscription -> subscription.getBookId()).collect(Collectors.toList());
			// /getallsubscribedbook
			String url =bookServiceUrl+"/getallsubscribedbook";
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<?> result = restTemplate.postForEntity(url, bookIdList, List.class);
			return ResponseEntity.ok(result.getBody());
		}
		return ResponseEntity.badRequest().body(new MessageResponse("No subscribed books"));
	}
	
	public ResponseEntity<?> getSubscriptions(Long userId) {
		if(ObjectUtils.isEmpty(userId)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Enter user Id"));
		}
		Set<ReaderSubscription>subscriptions= getAllSubscriptions(userId);
		if(!subscriptions.isEmpty() && subscriptions!=null)
			return ResponseEntity.ok(subscriptions);
		
		return ResponseEntity.badRequest().body(new MessageResponse("No subscriptions for this user"));
	}
	
	public ResponseEntity<?> getASubscription(Long userId, Long subsId) {
		if(ObjectUtils.isEmpty(userId)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Enter user Id"));
		}
		ReaderSubscription subscription= getSubscription(userId,subsId);
		if(!(ObjectUtils.isEmpty(subscription)) && subscription!=null)
			return ResponseEntity.ok(subscription);
		
		return ResponseEntity.badRequest().body(new MessageResponse("No such subscription exist for this user"));
	}
	
	public ResponseEntity<?> getSubscriptionOfBook(Long userId, Long bookId) {
		if(ObjectUtils.isEmpty(userId)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Please give user Id"));
		}
		if(ObjectUtils.isEmpty(bookId)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Please give book Id"));
		}
		Optional<ReaderSubscription> subscription = susbscriptionRepo.findByBookIdAndUserId(bookId, userId);
		if(!(ObjectUtils.isEmpty(subscription)) && subscription!=null) {
			return ResponseEntity.ok(subscription);
		}
		return ResponseEntity.badRequest().body(new MessageResponse("No such subscription exist for this user and book combination"));
	}

}
