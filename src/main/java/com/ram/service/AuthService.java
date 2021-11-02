package com.ram.service;

import org.springframework.http.ResponseEntity;

import com.ram.model.APIResponse;
import com.ram.model.SigninRequest;
import com.ram.model.SignupRequest;

public interface AuthService {
	
	ResponseEntity<APIResponse> signin(SigninRequest request);
	
	ResponseEntity<APIResponse> signup(SignupRequest request);

}
