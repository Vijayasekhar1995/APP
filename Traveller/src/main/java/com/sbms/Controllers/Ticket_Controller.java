package com.sbms.Controllers;

import java.util.List;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbms.Entitys.StationsList;
import com.sbms.Entitys.Stations_bw_TwoStations;
import com.sbms.Entitys.FinalTicketResponse;
import com.sbms.Entitys.TicketRequest;
import com.sbms.ServicesI.Ticket_ServicesI;
import com.sbms.ServicesImpl.Ticket_ServicesImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Ticket_Controller {

	@Autowired
	Ticket_ServicesI ticket_ServicesI;

	@Autowired
	Stations_bw_TwoStations stations_bw_TwoStations;

	@Autowired
	Environment environment;

	@RequestMapping(method = RequestMethod.POST, value = "/bookTicket", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<FinalTicketResponse> bookTicket(@RequestBody TicketRequest ticketRequest) {

		System.out.println("ticketRequest is :: " + ticketRequest);

		FinalTicketResponse ticket = ticket_ServicesI.bookTicket(ticketRequest);
		log.info("[ The bookTicket Request Processing by the Traveller-APPLICATION which run's on the Port :: "
				+ environment.getProperty("server.port") + " ]");
		return new ResponseEntity<FinalTicketResponse>(ticket, HttpStatus.CREATED);

	}

	@GetMapping(value = "/getTicket/{pnr}")
	public ResponseEntity getTicket(@PathVariable Integer pnr) {

		Object object = ticket_ServicesI.getTicket(pnr);

		if (object instanceof FinalTicketResponse) {
			return new ResponseEntity<FinalTicketResponse>((FinalTicketResponse) object, HttpStatus.FOUND);
		}
		return new ResponseEntity<String>((String) object, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getAvailable_Stations_between_Two_Stations")
	public ResponseEntity<StationsList> getAvailable_Stations_between_Two_Stations(@RequestParam String from_station,
			@RequestParam String to_station, @RequestParam Integer train_no) {

		StationsList available_Stations_between_Two_Stations = ticket_ServicesI.getAvailable_Stations_between_Two_Stations(from_station,
				to_station, train_no);
		return new ResponseEntity<StationsList>(available_Stations_between_Two_Stations, HttpStatus.OK);
		
		

	}
	@PutMapping("/updateTicket")
	public ResponseEntity updateTicket(@RequestBody FinalTicketResponse ticket1) {

		ticket1.setBoarding_station(ticket1.getBoarding_station().toUpperCase());
		System.out.println("Ticket1 is :: " + ticket1);

		ResponseEntity ticket = this.getTicket(ticket1.getPnr());
		Object body1 = ticket.getBody();
		if (body1 instanceof FinalTicketResponse) {
			
			FinalTicketResponse ticket2 = (FinalTicketResponse) body1;
			ResponseEntity<StationsList> available_Stations_between_Two_Stations = this.getAvailable_Stations_between_Two_Stations(ticket2.getBoarding_station(), ticket1.getTo_station(), ticket1.getTrain_no());
			StationsList body2 = available_Stations_between_Two_Stations.getBody();
			List<String> stationsList = body2.getStationsList();
			if (stationsList.contains(ticket1.getBoarding_station())) {
				
				
				FinalTicketResponse updateTicket = ticket_ServicesI.updateTicket(ticket1);
				return new ResponseEntity<FinalTicketResponse>(updateTicket,HttpStatus.OK);
				
			}
			
			String string = stationsList.toString();
			return new ResponseEntity<String>(
					"Hey Man !!!, You Choosen the Wrong *** Boarding Station *** !!!, \n The available Stations between "
							+ ticket2.getBoarding_station() + " And "
							+ ticket1.getTo_station() + " is :: \n " + string,
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Invalid pnr number !!!!!!!", HttpStatus.BAD_REQUEST);
	}

	
}
