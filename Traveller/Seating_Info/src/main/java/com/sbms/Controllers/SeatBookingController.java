package com.sbms.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sbms.Entitys.Passenger;
import com.sbms.Entitys.SeatBookingRequest;
import com.sbms.ServicesI.SeatBookingServiceI;

@RestController
public class SeatBookingController {
	
	@Autowired
	SeatBookingServiceI seatBookingServiceI;
	
	@PostMapping("/seatBooking")
	public ResponseEntity<List<Passenger>> seatBooking(@RequestBody SeatBookingRequest seatBookingRequest) throws JsonProcessingException {
		
		System.out.println("seatBookingRequest :: "+seatBookingRequest);
		
		List<Passenger> seatBookingList = seatBookingServiceI.seatBooking(seatBookingRequest);
		System.out.println("seatBookingList is :: "+seatBookingList);
		System.out.println("seatBookingRequest :: "+seatBookingRequest);
		return new ResponseEntity<List<Passenger>>(seatBookingList,HttpStatus.CREATED);
		
	}

}
