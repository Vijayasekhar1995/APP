package com.sbms.FeignClients;

import java.util.HashMap;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sbms.Entitys.Availabe_Trains;
import com.sbms.Entitys.Booking_info;
import com.sbms.Entitys.InitialTicketResponse;
import com.sbms.Entitys.Passenger;
import com.sbms.Entitys.Seat;
import com.sbms.Entitys.SeatBookingRequest;
import com.sbms.Entitys.SeatBookingResponse;
import com.sbms.Entitys.SeatingAvailabilityRequest;
import com.sbms.Entitys.StationsList;
import com.sbms.Entitys.Stations_bw_TwoStations;
import com.sbms.Entitys.Train_Search_Request;

@FeignClient(name="Seating-Info")
public interface FeignClientToTSeatingInfoApplication {
	
	@PostMapping("/seatBooking")
	List<Passenger> seatBooking(SeatBookingRequest seatBookingRequest);
	
	@PostMapping("/seating_information")
	HashMap<Integer, Seat> get_number_of_Seats_Available_In_EachTrain(SeatingAvailabilityRequest seatingAvailabilityRequest);
	

}
