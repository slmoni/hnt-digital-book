package com.bezkoder.springjwt.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.BookRequest;
import com.bezkoder.springjwt.payload.request.LoginRequest;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.payload.request.SubscriptionRequest;
import com.bezkoder.springjwt.payload.response.JwtResponse;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/digitalbooks")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	ObjectMapper mapper;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName("ROLE_GUEST")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "reader":
					Role reader = roleRepository.findByName("ROLE_READER")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(reader);

					break;
				case "author":
					Role author = roleRepository.findByName("ROLE_AUTHOR")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(author);

					break;
				default:
					Role userRole = roleRepository.findByName("ROLE_GUEST")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
					
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	
	@PostMapping("/author/{authorId}/createbook")
	public ResponseEntity<?> createBook(HttpServletRequest request, @Valid @RequestBody BookRequest book, @PathVariable Long authorId) throws JsonParseException, JsonMappingException, IOException{
		if(authorId==null) {
			return ResponseEntity.badRequest().body("Please give valid author Id");
		}
		return null;
		
	}
	
	@PutMapping("author/{author-id}/updatebook/{book-id}")
	@PreAuthorize("hasRole('AUTHOR')")
	public ResponseEntity<?> updatebook(@RequestBody BookRequest book, @PathVariable Long bookId, @PathVariable Long authorId) {
		return null;
	}
	
	@PostMapping("digitalbooks/author/{author-id}/books/{book-id}/{isBlocked}")
	@PreAuthorize("hasRole('AUTHOR')")
	public ResponseEntity<?> blockBook(@PathVariable Long authorId, @PathVariable Long bookId, @PathVariable String isBlocked) {
		return null;
	}
	
	@GetMapping("/search")
	public ResponseEntity<?>searchBooks(@PathVariable String category, @PathVariable String title, 
			@PathVariable String author, @PathVariable Long price, @PathVariable String publisher) {
		return null;
	}
	
	@PostMapping("/{book-id}/subscribe")
	@PreAuthorize("hasRole('READER')")
	public ResponseEntity<?>subscribeBook(@RequestBody SubscriptionRequest subscription, @PathVariable Long userId) {
		return null;
	} 
	
	@PostMapping("/readers/{userId}/books/{subscription-id}/cancel-subscription")
	@PreAuthorize("hasRole('READER')")
	public ResponseEntity<?>cancelSubscription(@PathVariable Long userId, @PathVariable Long subId) {
		return null;
	}
	
	@PostMapping("/readers/{userId}/books")
	@PreAuthorize("hasRole('READER')")
	public ResponseEntity<?>fectchAllSubscribedBook(@PathVariable Long userId) {
		return null;
	} 
}
