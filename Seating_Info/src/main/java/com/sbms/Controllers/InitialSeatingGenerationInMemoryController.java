package com.sbms.Controllers;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbms.Entitys.Seating_Information_InMemory;
import com.sbms.Entitys.TrainCoachInfo;
import com.sbms.ServicesI.InitialSeatingGenerationInMemoryServicesI;

@RestController
public class InitialSeatingGenerationInMemoryController {
	
	@Autowired
	InitialSeatingGenerationInMemoryServicesI initialSeatingGenerationServicesI;
	
	@PostMapping("/create_initial_seating_inmemory")
	public ResponseEntity<LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>>>> initialSeatingGeneration(@RequestBody List<TrainCoachInfo> trainCoachInfoList) {
		LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>>> initialSeatingGeneration = initialSeatingGenerationServicesI.initialSeatingGeneration(trainCoachInfoList);
		return new ResponseEntity<LinkedHashMap<String,LinkedHashMap<String,LinkedHashMap<String,Seating_Information_InMemory[]>>>>(initialSeatingGeneration,HttpStatus.CREATED);	
	}
	
	@PostMapping("/create_daily_seating_inmemory")
	public ResponseEntity<LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>>>> dailySeatingGeneration(@RequestBody List<TrainCoachInfo> trainCoachInfoList) {
		LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>>> dailySeatingGeneration = initialSeatingGenerationServicesI.dailySeatingGeneration(trainCoachInfoList);
		return new ResponseEntity<LinkedHashMap<String,LinkedHashMap<String,LinkedHashMap<String,Seating_Information_InMemory[]>>>>(dailySeatingGeneration,HttpStatus.CREATED);	
	}

}
