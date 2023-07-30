package com.sbms.ServicesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.sbms.Entitys.StationsList;
import com.sbms.Entitys.FinalTicketResponse;
import com.sbms.Entitys.TicketRequest;
import com.sbms.Repository.TicketRepository;
import com.sbms.ServicesI.Ticket_ServicesI;

@Service
public class Ticket_ServicesImpl implements Ticket_ServicesI {

	@Autowired
	TicketRepository ticketRepository;

	@Override
	public FinalTicketResponse bookTicket(TicketRequest ticketRequest) {

		/* Connecting to Bharat Rail-Application through WebClient */
		String URL = "http://localhost:2000/sbms/bharat/bookTicket";
		FinalTicketResponse ticket = WebClient.create().post().uri(URL)
				.headers(headers -> headers.setBasicAuth("traveller", "traveller123")).bodyValue(ticketRequest).retrieve()
				.bodyToMono(FinalTicketResponse.class).block();

		ticket.setProvider("Traveller");
		FinalTicketResponse save = ticketRepository.save(ticket);
		return save;
	}

	@Override
	public Object getTicket(Integer integer) {

		Object object = null;

		Optional<FinalTicketResponse> findById = ticketRepository.findById(integer);
		if (findById.isPresent()) {
			return object = findById.get();
		}
		return object = "Invalid pnr Number !!! ";

	}

	@Override
	public FinalTicketResponse updateTicket(FinalTicketResponse ticket) {
		ticket.setProvider("Bharat Rail");

//		/* Connecting to Bharat Rail-Application through WebClient */
		String URL = "http://localhost:2000/sbms/bharat/updateTicket";
//		Ticket ticket2 = WebClient.create()
//				.put()
//				.uri(URL)
//				.headers(headers -> headers
//				.setBasicAuth("yatra", "yatra123"))
//				.bodyValue(Ticket.class)
//				.retrieve()
//				.bodyToMono(Ticket.class)
//				.block();
//		Ticket save = ticketRepository.save(ticket2);
//		return save;

//		/* Connecting to Bharat Rail -Application through RestTemplate */
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("traveller", "traveller123");
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<FinalTicketResponse> httpEntity = new HttpEntity<FinalTicketResponse>(ticket, httpHeaders);

		ResponseEntity<FinalTicketResponse> exchange = restTemplate.exchange(URL, HttpMethod.PUT, httpEntity,
				FinalTicketResponse.class);
		FinalTicketResponse body = exchange.getBody();
		body.setProvider("Traveller");
		FinalTicketResponse save = ticketRepository.save(body);
		

		return save;
	}

	@Override
	public StationsList getAvailable_Stations_between_Two_Stations(String from_station, String to_station,
			Integer train_no) {

		/* Connecting to bharat-Application through WebClient */
		String URL = "swapna:2000/sbms/train/getAvailable_Stations_between_Two_Stations?from_station=" + from_station
				+ "&to_station=" + to_station + "&train_no=" + train_no;
		StationsList stationsList = WebClient.create().get().uri(URL)
//				.headers(headers -> headers.setBasicAuth("yatra", "yatra123"))
				.retrieve().bodyToMono(StationsList.class).block();

		return stationsList;

	}

}
