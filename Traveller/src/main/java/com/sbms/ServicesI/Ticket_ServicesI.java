package com.sbms.ServicesI;

import com.sbms.Entitys.FinalTicketResponse;
import com.sbms.Entitys.StationsList;
import com.sbms.Entitys.TicketRequest;

public interface Ticket_ServicesI {

	public FinalTicketResponse bookTicket(TicketRequest ticketRequest);

	public Object getTicket(Integer integer);

	public FinalTicketResponse updateTicket(FinalTicketResponse ticket);

	public StationsList getAvailable_Stations_between_Two_Stations(String from_station, String to_station, Integer train_no);

}
