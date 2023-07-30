package com.sbms.ServicesImpl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sbms.Entitys.Availabe_Trains;
import com.sbms.Entitys.Train_Search_Request;
import com.sbms.ServicesI.Train_ServicesI;

@Service
public class Train_ServicesImpl implements Train_ServicesI{
	
	

	@Override
	public Availabe_Trains train_search(String from_station, String to_station, String date) {
		
	
		/* Connecting to Bharat Rail Application through WebClient */
		String  URL = "http://swapna:2000/sbms/bharat/train_search?from_station="+from_station+"&to_station="+to_station+"&date="+date;
		Availabe_Trains availabe_Trains = WebClient.create()
												   .get()
		                                           .uri(URL)
		                                           .retrieve()
		                                           .bodyToMono(Availabe_Trains.class)
		                                           .block();
				 
		availabe_Trains.getAvailable_Trains_List().forEach(e->e.setProvider("Traveller"));
		return availabe_Trains;
	}

}
