package com.sbms.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@GetMapping("/welcome")
	public ResponseEntity<String> home() {
		return new ResponseEntity<String>("Welcome page of Bharat Rail APPLICATION", HttpStatus.OK);
	}
	
}
