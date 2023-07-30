package com.sbms.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.sbms.Entitys.Availabe_Trains;
import com.sbms.Entitys.Booking_info;
import com.sbms.Entitys.InitialTicketResponse;
import com.sbms.Entitys.StationsList;
import com.sbms.Entitys.Stations_bw_TwoStations;
import com.sbms.Entitys.Train_Search_Request;

@FeignClient(name = "Train-App")
public interface FeignClientToTrainApplication {

	@PostMapping("bookTicket")
	InitialTicketResponse bookTicket(Booking_info booking_info);

	@PostMapping("train_search")
	Availabe_Trains train_search(Train_Search_Request train_Search_Request);

	@PostMapping("getAvailable_Stations_between_Two_Stations")
	public StationsList getAvailable_Stations_between_Two_Stations(Stations_bw_TwoStations stations_bw_TwoStations);


}
