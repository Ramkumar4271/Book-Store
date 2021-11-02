package com.ram.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ram.entity.Role;
import com.ram.entity.User;
import com.ram.model.APIResponse;
import com.ram.model.SigninRequest;
import com.ram.model.SigninResponse;
import com.ram.model.SignupRequest;
import com.ram.model.UserDetailsImpl;
import com.ram.repository.RoleRepository;
import com.ram.repository.UserRepository;
import com.ram.util.JwtUtils;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Override
	public ResponseEntity<APIResponse> signin(SigninRequest request) {

		Optional<User> user = userRepository.findByEmail(request.getEmail());

		if (user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(APIResponse.builder().status(HttpStatus.BAD_REQUEST).error("Email Id is not exist!").build());
		}

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.get().getUsername(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK)
				.body(APIResponse.builder().status(HttpStatus.OK)
						.data(SigninResponse.builder().token(jwt).id(userDetails.getId())
								.username(userDetails.getUsername()).email(userDetails.getEmail()).roles(roles).build())
						.build());
	}

	@Override
	public ResponseEntity<APIResponse> signup(SignupRequest request) {

		if (userRepository.existsByUsername(request.getUsername())) {
			return ResponseEntity.badRequest().body(
					APIResponse.builder().status(HttpStatus.BAD_REQUEST).error("Username is already taken!").build());
		}

		if (userRepository.existsByEmail(request.getEmail())) {
			return ResponseEntity.badRequest().body(
					APIResponse.builder().status(HttpStatus.BAD_REQUEST).error("Email is already in use!").build());
		}

		// Create new user's account
		User user = User.builder().username(request.getUsername()).email(request.getEmail())
				.password(encoder.encode(request.getPassword())).build();

		Set<String> strRoles = request.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null || strRoles.size() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(APIResponse.builder().status(HttpStatus.BAD_REQUEST).error("Role is not found.").build());
		} else {
			for (String role : strRoles) {
				String temp = role.toUpperCase();
				Optional<Role> r = roleRepository.findByName(temp);

				if (r.isEmpty()) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(APIResponse.builder()
							.status(HttpStatus.BAD_REQUEST).error(role + " role is not found.").build());
				}
				roles.add(r.get());
			}
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.status(HttpStatus.OK)
				.body(APIResponse.builder().status(HttpStatus.OK).data("User registered successfully!").build());

	}

}
