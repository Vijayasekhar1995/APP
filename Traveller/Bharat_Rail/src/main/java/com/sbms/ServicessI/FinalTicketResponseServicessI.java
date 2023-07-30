package com.sbms.ServicessI;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.sbms.Entitys.FinalTicketResponse;
import com.sbms.Entitys.StationsList;
import com.sbms.Entitys.Stations_bw_TwoStations;
import com.sbms.Entitys.TicketRequest;

public interface FinalTicketResponseServicessI {
	
	public FinalTicketResponse bookTicket(TicketRequest ticket);
	
	public String deleteTicket(Integer pnr);
	
	public Optional<FinalTicketResponse> getTicket(Integer pnr);
	
	public ResponseEntity updateTicket(FinalTicketResponse  finalTicketResponse1, FinalTicketResponse finalTicketResponse2) throws CloneNotSupportedException;

	public StationsList getAvailable_Stations_between_Two_Stations(Stations_bw_TwoStations stations_bw_TwoStations);

}
