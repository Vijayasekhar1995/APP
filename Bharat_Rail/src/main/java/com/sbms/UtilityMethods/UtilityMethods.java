package com.sbms.UtilityMethods;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.sbms.Entitys.FinalTicketResponse;

@Component
public class UtilityMethods {

	public String get_Total_J_H_Bw_BoardingAndToStation(FinalTicketResponse finalTicketResponse1) {

		String boarding_date = finalTicketResponse1.getBoarding_date();
		String dept_at_boarding_station = finalTicketResponse1.getDept_at_boarding_station();

		String to_date = finalTicketResponse1.getTo_date();
		String arri_at_to_station = finalTicketResponse1.getArri_at_to_station();

		String dateAndTimeAtBoardingStation = boarding_date + " " + dept_at_boarding_station;
		String dateAndTimeAtToStation = to_date + " " + arri_at_to_station;

		LocalDateTime localDateTimeAtBoardingStation = LocalDateTime.parse(dateAndTimeAtBoardingStation,
				DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		LocalDateTime localDateTimeAtToStation = LocalDateTime.parse(dateAndTimeAtToStation,
				DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

		Duration between = Duration.between(localDateTimeAtBoardingStation, localDateTimeAtToStation);
		System.out.println(between);

		long minutes = between.toMinutes();
		int hours = (int) (minutes / 60);
		int mins = (int) (minutes - (hours * 60));
		String total_J_H_from_boarding_station = hours + ":" + mins + ":" + "00";
		return total_J_H_from_boarding_station;

	}

}
