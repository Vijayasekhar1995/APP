package com.sbms.ServicesI;

import java.util.HashMap;
import java.util.List;

import com.sbms.Entitys.Seat;

public interface SeatingAvailabilityInfoServiceI {
	
	public HashMap<Integer, Seat> get_number_of_Seats_Available_In_EachTrain(List<Integer> list, String date);

}
