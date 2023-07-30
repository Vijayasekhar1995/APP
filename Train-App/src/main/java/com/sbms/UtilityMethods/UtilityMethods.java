package com.sbms.UtilityMethods;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbms.Entitys.Train;
import com.sbms.Repositorys.TrainRepository;

@Component
public class UtilityMethods {

	@Autowired
	TrainRepository trainRepository;

	public HashMap<Integer, String> getAvailableTrainsBetweenTwoStationsInOneWay(String from_station,
			String to_station) {
		HashMap<Integer, String> availabletrains = new HashMap<>();
		List<String> findTrainsAtFromStationWithoutFiltering = trainRepository
				.findTrainsAtFromStationWithoutFiltering(from_station);

		List<String> findTrainsToStationWithFiltering = trainRepository.findTrainsToStationWithFiltering(to_station,
				findTrainsAtFromStationWithoutFiltering);

		List<String> findTrainsAtFromStationWithFiltering = trainRepository
				.findTrainsAtFromStationWithFiltering(from_station, findTrainsToStationWithFiltering);

		List<String> trainsBetweenSelectedFromStationAndToStation = findTrainsAtFromStationWithFiltering;

		System.out.println("The Available train names  between from " + from_station + " to " + to_station + " is :: "
				+ trainsBetweenSelectedFromStationAndToStation);

		for (String string : trainsBetweenSelectedFromStationAndToStation) {
			System.out.println("Train Name :: " + string);
			Integer findTrainNumber = trainRepository.findTrainNumber(string);
			availabletrains.put(findTrainNumber, string);
		}
		System.out.println("The Available train no's  between from " + from_station + " to " + to_station + " is :: "
				+ availabletrains);

		return availabletrains;
	}



	public HashMap<Integer, String> getAvailableTrainsBetweenTwoStationsInTwoWay(String from_station,
			String to_station) {
		HashMap<Integer, String> availabletrains = new HashMap<>();
		List<Integer> findTrainNumbersAtFromStationWithoutFiltering = trainRepository
				.findTrainNumbersAtFromStationWithoutFiltering(from_station);

		List<Integer> findTrainNumbersToStationWithFiltering = trainRepository
				.findTrainNumbersToStationWithFiltering(to_station, findTrainNumbersAtFromStationWithoutFiltering);

		List<Integer> findTrainNumbersAtFromStationWithFiltering = trainRepository
				.findTrainNumbersAtFromStationWithFiltering(from_station, findTrainNumbersToStationWithFiltering);

		List<Integer> trainNumbersBetweenSelectedFromStationAndToStation = findTrainNumbersAtFromStationWithFiltering;

		System.out.println("The Available train numbers  between from " + from_station + " to " + to_station + " is :: "
				+ trainNumbersBetweenSelectedFromStationAndToStation);

		for (Integer integer : trainNumbersBetweenSelectedFromStationAndToStation) {
			System.out.println("Train Number :: " + integer);
			String findTrainName = trainRepository.findTrainName(integer);
			availabletrains.put(integer, findTrainName);
		}
		System.out.println("The Available train's  between from " + from_station + " to " + to_station + " is :: "
				+ availabletrains);

		return availabletrains;
	}

	public String getTo_date(Train train_At_From_Station, Train train_At_To_Station, String from_Date) {

		Integer train_day_at_from_station = train_At_From_Station.getDay();
		Integer train_day_at_to_station = train_At_To_Station.getDay();
		String[] from_date_variables = from_Date.split("-");

		LocalDate from_date = LocalDate.of(Integer.parseInt(from_date_variables[2]),
				Integer.parseInt(from_date_variables[1]), Integer.parseInt(from_date_variables[0]));
		LocalDate to_date = from_date.plusDays(train_day_at_to_station - train_day_at_from_station);

		String format = to_date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		return format;

	}

	public String getTotal_Journey_Hours(Train train_At_From_Station, Train train_At_To_Station, String from_Date, String to_date) {

		String[] from_date_variables = from_Date.split("-");
		String[] to_date_variables = to_date.split("-");
		String[] time_variables_at_from_station = train_At_From_Station.getDeparture().split(":");
		String[] time_variables_at_to_station = train_At_To_Station.getArrival().split(":");

		LocalDateTime from_dateTime = LocalDateTime.of(Integer.parseInt(from_date_variables[2]),
				Integer.parseInt(from_date_variables[1]), Integer.parseInt(from_date_variables[0]),
				Integer.parseInt(time_variables_at_from_station[0]),
				Integer.parseInt(time_variables_at_from_station[1]));
		
		LocalDateTime to_dateTime = LocalDateTime.of(Integer.parseInt(to_date_variables[2]),
				Integer.parseInt(to_date_variables[1]), Integer.parseInt(to_date_variables[0]),
				Integer.parseInt(time_variables_at_to_station[0]),
				Integer.parseInt(time_variables_at_to_station[1]));
		String durationBetweenTwoDates = this.getDurationBetweenTwoDates(from_dateTime, to_dateTime);
		
		return durationBetweenTwoDates;
	
	}
	
	private String getDurationBetweenTwoDates(LocalDateTime from_dateTime,
			LocalDateTime to_dateTime) {
		Duration between = Duration.between(from_dateTime, to_dateTime);
		long minutes = between.toMinutes();
		int hours = (int) (minutes / 60);
		int mins = (int) (minutes - (hours * 60));
		String totalJourneyTime = hours + ":" + mins + ":" + "00";
		return totalJourneyTime;
	}

}
