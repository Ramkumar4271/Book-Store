package com.ram.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest {

	@NotBlank(message = "Please provide email")
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank(message = "Please provide password")
	@Size(max = 120)
	private String password;
}
