package com.sbms.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbms.Entitys.StationsList;
import com.sbms.Entitys.Stations_bw_TwoStations;
import com.sbms.Entitys.TicketRequest;
import com.sbms.Entitys.TicketResponse;
import com.sbms.ServicesImpl.TicketServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TicketController {

	@Autowired
	TicketServiceImpl ticketServiceImpl;

	@Autowired
	Environment environment;

	@Autowired
	StationsList stationsList;

	@Autowired
	TicketRequest booking_infojava;

	@PostMapping(value = "/bookTicket", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<TicketResponse> bookTicket(@RequestBody TicketRequest booking_info) {

		TicketResponse bookTicket = ticketServiceImpl.bookTicket(booking_info);

		log.info("[ The bookTicket Request Processing by the TRAIN-APPLICATION which run's on the Port :: "
				+ environment.getProperty("server.port") + " ]");
		return new ResponseEntity<TicketResponse>(bookTicket, HttpStatus.OK);

	}

	@GetMapping("/getAvailable_Stations_between_Two_Stations")
	public ResponseEntity<StationsList> getAvailable_Stations_between_Two_Stations(@RequestParam String from_station,
			@RequestParam String to_station, @RequestParam Integer train_no) {

		List<String> available_Stations_between_Two_Stations = ticketServiceImpl
				.getAvailable_Stations_between_Two_Stations(from_station.toUpperCase(), to_station.toUpperCase(),
						train_no);
		return new ResponseEntity<StationsList>(new StationsList(available_Stations_between_Two_Stations),
				HttpStatus.OK);

	}

	@PostMapping("/getAvailable_Stations_between_Two_Stations")
	public ResponseEntity<StationsList> get_Available_Stations_between_Two_Stations(
			@RequestBody Stations_bw_TwoStations stations_bw_TwoStations) {
		System.out.println(stations_bw_TwoStations);

		ResponseEntity<StationsList> available_Stations_between_Two_Stations = this
				.getAvailable_Stations_between_Two_Stations(stations_bw_TwoStations.getFrom_station().toUpperCase(),
						stations_bw_TwoStations.getTo_station().toUpperCase(), stations_bw_TwoStations.getTrain_no());
		return available_Stations_between_Two_Stations;

	}

}
