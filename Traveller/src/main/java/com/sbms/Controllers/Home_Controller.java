package com.sbms.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home_Controller {
	
	@GetMapping("/home")
	public ResponseEntity<String> home(){
		
		return new ResponseEntity<String>(
			
				"***   Hi Welcome to Traveller Application  ***\n"+
				"\n"+													
				"We are providing Train services\n"+                                    
				"\n"+
				"To Seach trains between Two Stations Plese visit the  /train_search?from_station=station-name&to_station=station-name&date=dd-mm-yyyy\n"+
				"\n"+
				"To  Book  the  Train  ticket  Plese  visit this link  /bookTicket  with POST requesr by passing below JSON Body\n"+
				"\n"+
				"{\r\n"
				+ "    \"train_no\":12733,\r\n"
				+ "	\"from_station\":\"Kavali\",\r\n"
				+ "	\"to_station\":\"tenali\",\r\n"
				+ "	\"coach\":\"1ac\",\r\n"
				+ "	\"date\":\"23-11-2022\",\r\n"
				+ "    \"passenger\": {\r\n"
				+ "        \"name\": \"Vijay\",\r\n"
				+ "        \"gender\": \"Male\",\r\n"
				+ "        \"age\": 27,\r\n"
				+ "        \"address\": {\r\n"
				+ "            \"village\": \"Nellore\",\r\n"
				+ "            \"zipcode\": 524005\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}                                                                                     "
				, HttpStatus.OK
				);	
	}

}
