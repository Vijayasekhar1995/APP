package com.sbms.Controllers;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbms.Entitys.Seat;
import com.sbms.Entitys.SeatingAvailabilityRequest;
import com.sbms.ServicesI.SeatingAvailabilityInfoServiceI;

@RestController
public class SeatingAvailabilityInfoController {

	@Autowired
	SeatingAvailabilityInfoServiceI seatingAvailabilityInfoServiceI;

	@PostMapping("/seating_information")
	public ResponseEntity<HashMap<Integer, Seat>> get_number_of_Seats_Available_In_EachTrain(@RequestBody SeatingAvailabilityRequest seatingAvailabilityRequest) {
		HashMap<Integer, Seat> get_number_of_Seats_Available_In_EachTrain = seatingAvailabilityInfoServiceI
				.get_number_of_Seats_Available_In_EachTrain(seatingAvailabilityRequest.getList_of_trainnumbers(), seatingAvailabilityRequest.getDate());
		return new ResponseEntity<HashMap<Integer, Seat>>(get_number_of_Seats_Available_In_EachTrain, HttpStatus.OK);

	}

}
