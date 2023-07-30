package com.sbms.ServicessImpl;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbms.Entitys.Availabe_Trains;
import com.sbms.Entitys.Seat;
import com.sbms.Entitys.SeatingAvailabilityRequest;
import com.sbms.Entitys.Train_Search_Request;
import com.sbms.FeignClients.FeignClientToTSeatingInfoApplication;
import com.sbms.FeignClients.FeignClientToTrainApplication;
import com.sbms.ServicessI.TrainServicesI;

@Service
public class TrainservicesImpl implements TrainServicesI {

	@Autowired
	FeignClientToTrainApplication feignClientToTrainApplication;

	@Autowired
	FeignClientToTSeatingInfoApplication feignClientToTSeatingInfoApplication;
	
	@Autowired
	SeatingAvailabilityRequest seatingAvailabilityRequest;

	@Override
	public Availabe_Trains train_search(Train_Search_Request train_Search_Request) {

		/* Connecting to Train Application through Feign-Client */
		Availabe_Trains availabe_Trains = feignClientToTrainApplication.train_search(train_Search_Request);

		/* Connecting to Train Application through WebClient */
//		String url = "http://localhost:1011/train_search";
//		Availabe_Trains availabe_Trains = WebClient.create()
//				 .post()
//				 .uri(url)
//				 .bodyValue(train_Search_Request)
//				 .retrieve()
//				 .bodyToMono(Availabe_Trains.class)
//				 .block();

		List<Integer> list_of_trainnumbers = availabe_Trains.getAvailable_Trains_List().stream()
				.map(train_search_response -> train_search_response.getTrain_no()).collect(Collectors.toList());
		
		String from_date = availabe_Trains.getAvailable_Trains_List().get(0).getFrom_date();
		
		seatingAvailabilityRequest.setDate(from_date);
		seatingAvailabilityRequest.setList_of_trainnumbers(list_of_trainnumbers);
		
		
		/* Connecting to Seating info Application through Feign-Client to get number of Seats Available In EachTrain */
		HashMap<Integer, Seat> get_number_of_Seats_Available_In_EachTrain = feignClientToTSeatingInfoApplication
				.get_number_of_Seats_Available_In_EachTrain(seatingAvailabilityRequest);

		availabe_Trains.getAvailable_Trains_List().stream().forEach(train_search_response -> {

			get_number_of_Seats_Available_In_EachTrain.keySet().stream().forEach(train_no -> {

				if (train_search_response.getTrain_no().equals(train_no)) {
					train_search_response.setAvailable_seats_for_each_Coach(
							get_number_of_Seats_Available_In_EachTrain.get(train_no));
				}

			});

		});

		return availabe_Trains;
	}

}
