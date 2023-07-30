package com.sbms.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbms.Entitys.Availabe_Trains;
import com.sbms.Entitys.Train_Search_Request;
import com.sbms.ServicesI.Train_ServicesI;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class Train_Controller {

	@Autowired
	Train_ServicesI trainServicesI;
	
	@Autowired
	Environment environment;

	@GetMapping(value = "train_search",  produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Availabe_Trains> train_search(@RequestParam("from_station") String from_station,
			@RequestParam("to_station") String to_station, @RequestParam("date") String date) {
			
		Availabe_Trains availabe_Trains = trainServicesI.train_search(from_station , to_station, date);
		log.info("[ The train_search Request Processing by the Traveller-APPLICATION which run's on the Port :: "+environment.getProperty("server.port") +" ]");
		return new ResponseEntity<Availabe_Trains>(availabe_Trains, HttpStatus.FOUND);
	}

}
