package com.sreekanth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sreekanth.dto.LoginRequest;
import com.sreekanth.dto.RegisterRequest;
import com.sreekanth.entity.User;
import com.sreekanth.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		User user = userService.authenticate(loginRequest.getUserName(), loginRequest.getPassword());
		
		if (user == null || !user.getRole().equals(loginRequest.getRole())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
		}
		String token = userService.generateToken(user);
		return ResponseEntity.ok(token);
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
		try {
			User user = userService.register(registerRequest);
			return ResponseEntity.ok("User registered successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
