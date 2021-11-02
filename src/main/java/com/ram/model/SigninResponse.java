package com.ram.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SigninResponse {

	private String token;
	private long id;
	private String username;
	private String email;
	private List<String> roles = new ArrayList<>();
}
