package com.sbms.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@Autowired
	Environment environment;
	
	@GetMapping("/home")
	public ResponseEntity<String> home() {
		
		return new ResponseEntity<String>("**** [ Home page of Bharat Rail APPLICATION which run's on the Port :: "+environment.getProperty("server.port") +" ] ****", HttpStatus.OK);
		
	}

}
