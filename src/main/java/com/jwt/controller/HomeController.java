package com.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String welcome() {
		String text = "Hello";
		text += "Rajat";
		return text;
	}
	
	@GetMapping("/getUser")
	public String getUsers() {
		return "{\"nam\": \"Rajat\"}";
	}
}
