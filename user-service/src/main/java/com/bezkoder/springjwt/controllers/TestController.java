package com.bezkoder.springjwt.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('AUTHOR') or hasRole('READER')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/auth")
	@PreAuthorize("hasRole('AUTHOR')")
	public String moderatorAccess() {
		return "Author Board.";
	}

	@GetMapping("/reader")
	@PreAuthorize("hasRole('READER')")
	public String adminAccess() {
		return "Reader Board.";
	}
}
