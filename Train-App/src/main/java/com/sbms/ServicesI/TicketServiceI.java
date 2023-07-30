package com.sbms.ServicesI;

import java.util.List;

import com.sbms.Entitys.TicketRequest;
import com.sbms.Entitys.TicketResponse;

public interface TicketServiceI {
	
	public TicketResponse bookTicket(TicketRequest booking_info);
	
	public List<String> getAvailable_Stations_between_Two_Stations(String from_station,String to_station,Integer train_no);
	
}
