package com.ram.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ram.model.APIResponse;
import com.ram.model.SigninRequest;
import com.ram.model.SignupRequest;
import com.ram.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService service;
	
	@PostMapping("/signup")
	public ResponseEntity<APIResponse> signup(@Valid @RequestBody SignupRequest request) {
		return service.signup(request);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<APIResponse> signin(@Valid @RequestBody SigninRequest request) {
		return service.signin(request);
	}
	

}
