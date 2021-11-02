package com.ram.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.ram.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
	
	@NotBlank(message = "Please provide username")
	@Size(max = 20)
	private String username;

	@NotBlank(message = "Please provide email")
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank(message = "Please provide password")
	@Size(max = 120)
	private String password;

	private Set<String> roles = new HashSet<>();

}
