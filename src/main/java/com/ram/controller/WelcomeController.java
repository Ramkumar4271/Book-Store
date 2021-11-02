package com.ram.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@Value("${application.name}")
	private String appName;

	@GetMapping("/home")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public String getMessage() {
		return "Welcome to " + appName;
	}

}
