package com.sbms.Controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbms.Entitys.Seating_Information;
import com.sbms.Entitys.TrainCoachInfo;
import com.sbms.ServicesI.SeatingGenerationServiceI;

@RestController
public class SeatingGenerationController {
	
	@Autowired
	SeatingGenerationServiceI seatingGenerationServiceI;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@PostMapping("/create_initial_seating")
	public ResponseEntity<List<Seating_Information>> initialSeatingGeneration(@RequestBody List<TrainCoachInfo> trainCoachInfoList) {
		List<Seating_Information> initialSeatingGeneration = seatingGenerationServiceI.initialSeatingGeneration(trainCoachInfoList);
		return new ResponseEntity<List<Seating_Information>>(initialSeatingGeneration, HttpStatus.CREATED);
	}
	
	@PostMapping("/create_daily_seating")
	public ResponseEntity<List<Seating_Information>> dailySeatingGeneration(@RequestBody List<TrainCoachInfo> trainCoachInfoList) throws JsonProcessingException {
		List<Seating_Information> dailySeatingGeneration = seatingGenerationServiceI.dailySeatingGeneration(trainCoachInfoList);
		System.out.println("dailySeatingGeneration on date "+LocalDate.now()+" is :: "+objectMapper.writeValueAsString(dailySeatingGeneration));
//		return new ResponseEntity<List<Seating_Information>>(dailySeatingGeneration, HttpStatus.CREATED);
		return null;
	}

}
