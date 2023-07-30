package com.sbms.ServicesImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbms.Entitys.Coach;
import com.sbms.Entitys.Train;
import com.sbms.Entitys.TrainCoachInfo;
import com.sbms.Entitys.Train_Search_Request;
import com.sbms.Entitys.Train_Search_Response;
import com.sbms.Repositorys.TrainRepository;
import com.sbms.ServicesI.TrainServiceI;
import com.sbms.UtilityMethods.UtilityMethods;

@Service
public class TrainServiceImpl implements TrainServiceI {

	@Autowired
	TrainRepository trainRepository;

	@Autowired
	UtilityMethods utilityMethods;

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public Train save(Train trian) {
		Train save = trainRepository.save(trian);
		return save;
	}

	@Autowired
	public TicketServiceImpl ticketServiceImpl;

	@Override
	public List<Train> findAll() {
		List<Train> findAll = trainRepository.findAll();
		return findAll;
	}

	@Override
	public List<Train> saveAll(List<Train> trainList) {
		List<Train> saveAll = trainRepository.saveAll(trainList);
		return saveAll;
	}

	@Override
	public Optional<Train> findById(Integer id) {
		Optional<Train> findById = trainRepository.findById(id);
		return findById;
	}

	public List<Train_Search_Response> trains_Search(Train_Search_Request train_Search_Request) throws JsonProcessingException {

		List<Train_Search_Response> train_search_response_list = new ArrayList<>();

		HashMap<Integer, String> availableTrainsBetweenTwoStations = utilityMethods
				.getAvailableTrainsBetweenTwoStationsInTwoWay(train_Search_Request.getFrom_station().toUpperCase(),
						train_Search_Request.getTo_station().toUpperCase());

		Set<Integer> availableTrainNumbersBetweenTwoStations = availableTrainsBetweenTwoStations.keySet();
		System.out.println("availableTrainNumbersBetweenTwoStations ::" + availableTrainNumbersBetweenTwoStations);

		for (Integer train_no : availableTrainNumbersBetweenTwoStations) {

			Train_Search_Response train_Search_Response = new Train_Search_Response();
			
			Coach coach = new Coach();
			
			List<Train> findTrains_At_from_station = trainRepository
					.findTrains_At_from_station(train_Search_Request.getFrom_station().toUpperCase(), train_no);
			
			System.out.println("findTrains_At_from_station : "+objectMapper.writeValueAsString(findTrains_At_from_station));
			
			List<Train> findTrains_At_to_station = trainRepository
					.findTrains_At_to_station(train_Search_Request.getTo_station().toUpperCase(), train_no);
			System.out.println("findTrains_At_to_station : "+objectMapper.writeValueAsString(findTrains_At_to_station));

			train_Search_Response.setArri_at_from_station(findTrains_At_from_station.get(0).getArrival());
			train_Search_Response.setArri_at_to_station(findTrains_At_to_station.get(0).getArrival());
			train_Search_Response.setDept_at_from_station(findTrains_At_from_station.get(0).getDeparture());
			train_Search_Response.setDept_at_to_station(findTrains_At_to_station.get(0).getDeparture());
			train_Search_Response.setFrom_date(train_Search_Request.getDate());
			train_Search_Response.setProvider("Bharat Rail");

//			Calling the Utility class to get to_date
			String to_date = utilityMethods.getTo_date(findTrains_At_from_station.get(0),
					findTrains_At_to_station.get(0), train_Search_Request.getDate());
			train_Search_Response.setTo_date(to_date);

			train_Search_Response.setTotal_distance(
					findTrains_At_to_station.get(0).getKm() - findTrains_At_from_station.get(0).getKm() + "KM");
			
//			Calling the Utility class to get Total Journey Hours
			String total_Journey_Hours = utilityMethods.getTotal_Journey_Hours(findTrains_At_from_station.get(0), findTrains_At_to_station.get(0),
					train_Search_Request.getDate(), to_date);
			train_Search_Response.setTotal_journey_hours(total_Journey_Hours);
			
			train_Search_Response.setTrain_name(availableTrainsBetweenTwoStations.get(train_no));
			train_Search_Response.setTrain_no(train_no);

			findTrains_At_from_station.stream().forEach(train1 -> {
				findTrains_At_to_station.stream().forEach(train2 -> {

					if (train1.getCoach().equals(train2.getCoach())) {
						Double fare_At_From_Station = train1.getAmount();
						Double fare_At_To_Station = train2.getAmount();
						Double fare_For_Bw_Stations = fare_At_To_Station - fare_At_From_Station;
						if (train1.getCoach().equals("GEN"))
							coach.set_GEN_(fare_For_Bw_Stations + "");
						else if (train1.getCoach().equals("2S"))
							coach.set_2S_(fare_For_Bw_Stations + "");
						else if (train1.getCoach().equals("CC"))
							coach.set_CC_(fare_For_Bw_Stations + "");
						else if (train1.getCoach().equals("SL"))
							coach.set_SL_(fare_For_Bw_Stations + "");
						else if (train1.getCoach().equals("3AC"))
							coach.set_3AC_(fare_For_Bw_Stations + "");
						else if (train1.getCoach().equals("2AC"))
							coach.set_2AC_(fare_For_Bw_Stations + "");
						else if (train1.getCoach().equals("1AC"))
							coach.set_1AC_(fare_For_Bw_Stations + "");
					}
				});
			});
			
			train_Search_Response.setCost_for_each_Coach(coach);
			train_search_response_list.add(train_Search_Response);
		}
		System.out.println("train_search_response_list :: "+objectMapper.writeValueAsString(train_search_response_list));
		
		List<Train_Search_Response> collect = train_search_response_list.stream().filter(train -> !train.getTotal_journey_hours().startsWith("-") && !train.getTotal_distance().startsWith("-")).collect(Collectors.toList());

		return collect;
	}

	@Override
	public List<Integer> getTrainNumbers() {
		List<Integer> trainNumbers = trainRepository.getTrainNumbers();
		return trainNumbers;
	}

	@Override
	public List<TrainCoachInfo> coachInfoofTrains(List<Integer> train_nos_list) {

		List<TrainCoachInfo> trainCoachInfoList = new ArrayList<>();

		for (Integer integer : train_nos_list) {

			TrainCoachInfo trainCoachInfo = new TrainCoachInfo();

			List<String> coachs = trainRepository.coachInfoofTrain(integer);
			trainCoachInfo.setTrain_no(integer);
			coachs.forEach(e -> {
				if (e.equals("GEN")) {
					trainCoachInfo.set_GEN_(1);
				} else if (e.equals("2S")) {
					trainCoachInfo.set_2S_(1);
				} else if (e.equals("CC")) {
					trainCoachInfo.set_CC_(1);
				} else if (e.equals("SL")) {
					trainCoachInfo.set_SL_(1);
				} else if (e.equals("3AC")) {
					trainCoachInfo.set_3AC_(1);
				} else if (e.equals("2AC")) {
					trainCoachInfo.set_2AC_(1);
				} else if (e.equals("1AC")) {
					trainCoachInfo.set_1AC_(1);
				}

			});

			trainCoachInfoList.add(trainCoachInfo);

		}

		return trainCoachInfoList;
	}
}
