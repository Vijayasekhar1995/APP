package com.sbms.Entitys;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Component
public class InitialTicketResponse {
	
	@NonNull
	private Integer train_no;
	@NonNull
	private String train_name;
	@NonNull
	private String from_station;
	@NonNull
	private String from_date;
	@NonNull
	private String arri_at_from_station;
	@NonNull
	private String dept_at_from_station;
	@NonNull
	private String boarding_station;
	@NonNull
	private String boarding_date;
	@NonNull
	private String arri_at_boarding_station;
	@NonNull
	private String dept_at_boarding_station;
	@NonNull
	private String to_station;
	@NonNull
	private String arri_at_to_station;
	@NonNull
	private String dept_at_to_station;
	@NonNull
	private String to_date;
	@NonNull
	private String total_journey_hours;
	@NonNull
	private String total_distance;
	@NonNull
	private Double cost;
	@NonNull
	private String coach;
	
}
