package com.sbms.ServicesImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbms.Entitys.TicketRequest;
import com.sbms.Entitys.TicketResponse;
import com.sbms.Entitys.Train;
import com.sbms.Repositorys.TrainRepository;
import com.sbms.ServicesI.TicketServiceI;
import com.sbms.UtilityMethods.UtilityMethods;

@Service
public class TicketServiceImpl implements TicketServiceI {


	@Autowired
	private TrainRepository trainRepository;

	@Autowired
	private TicketResponse ticketResponse;
	
	@Autowired
	UtilityMethods utilityMethods;

	@Override
	public TicketResponse bookTicket(TicketRequest booking_info) {
		
		System.out.println("Booking_info object is :: " + booking_info);
		Train findTrainAt_from_station = trainRepository.findTrainAt_from_station(
				booking_info.getFrom_station().toUpperCase(), booking_info.getCoach().toUpperCase(),
				booking_info.getTrain_no());
		Train findTrainAt_to_station = trainRepository.findTrainAt_to_station(
				booking_info.getTo_station().toUpperCase(), booking_info.getCoach().toUpperCase(),
				booking_info.getTrain_no());
		
		ticketResponse.setArri_at_boarding_station(findTrainAt_from_station.getArrival());
		ticketResponse.setArri_at_from_station(findTrainAt_from_station.getArrival());
		ticketResponse.setArri_at_to_station(findTrainAt_to_station.getArrival());
		ticketResponse.setBoarding_date(booking_info.getDate());
		ticketResponse.setBoarding_station(booking_info.getFrom_station().toUpperCase());
		ticketResponse.setCoach(findTrainAt_from_station.getCoach());
		ticketResponse.setCost(findTrainAt_to_station.getAmount() - findTrainAt_from_station.getAmount());
		ticketResponse.setDept_at_boarding_station(findTrainAt_from_station.getDeparture());
		ticketResponse.setDept_at_from_station(findTrainAt_from_station.getDeparture());
		ticketResponse.setDept_at_to_station(findTrainAt_to_station.getDeparture().toUpperCase());
		ticketResponse.setFrom_date(booking_info.getDate());
		ticketResponse.setFrom_station(booking_info.getFrom_station().toUpperCase());
		
//		Calling the Utility class to get to_date
		String to_date = utilityMethods.getTo_date(findTrainAt_from_station, findTrainAt_to_station, booking_info.getDate());
		ticketResponse.setTo_date(to_date);
		
		ticketResponse.setTo_station(booking_info.getTo_station());
		ticketResponse.setTotal_distance(findTrainAt_to_station.getKm() - findTrainAt_from_station.getKm()+ " KM");
		
//		Calling the Utility class to get Total Journey Hours
		String total_Journey_Hours = utilityMethods.getTotal_Journey_Hours(findTrainAt_from_station, findTrainAt_to_station,
				booking_info.getDate(), to_date);
		ticketResponse.setTotal_journey_hours(total_Journey_Hours);
		
		ticketResponse.setTrain_name(findTrainAt_from_station.getTrain_name());
		ticketResponse.setTrain_no(booking_info.getTrain_no());
		
		return ticketResponse;
	}

	

	@Override
	public List<String> getAvailable_Stations_between_Two_Stations(String from_station, String to_station,
			Integer train_no) {
		System.out.println("from_station : "+from_station+" :: "+"to_station : "+to_station+"  :: "+" train_no :"+train_no);
		Integer trainId_At_From_Station = trainRepository.getTrainId_At_From_Station(from_station, train_no);
		Integer trainId_At_to_Station = trainRepository.getTrainId_At_to_Station(to_station, train_no);
		
		List<String> available_Stations_between_Two_Stations = trainRepository
				.getAvailable_Stations_between_Two_Stations(trainId_At_From_Station, trainId_At_to_Station);
		
		available_Stations_between_Two_Stations.remove(from_station);
		available_Stations_between_Two_Stations.remove(to_station);

		return available_Stations_between_Two_Stations;
	}

}
