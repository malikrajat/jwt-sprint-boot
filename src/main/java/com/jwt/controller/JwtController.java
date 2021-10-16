package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.helper.JwtUtil;
import com.jwt.model.JwtRequest;
import com.jwt.model.JwtResponse;
import com.jwt.services.CustomUserDetailsServices;

@RestController
public class JwtController {

	@Autowired
	private CustomUserDetailsServices customUserDetailsServices;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception  {
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bed Credentials");
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bed Credentials");
		}
		 UserDetails userDetails = this.customUserDetailsServices.loadUserByUsername(jwtRequest.getUserName());
		 String token = this.jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
