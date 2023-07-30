package com.sbms.ServicesI;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sbms.Entitys.Passenger;
import com.sbms.Entitys.SeatBookingRequest;
import com.sbms.Entitys.SeatBookingResponse;

public interface SeatBookingServiceI {

	public List<Passenger> seatBooking(SeatBookingRequest seatBookingRequest) throws JsonProcessingException;
	
}
