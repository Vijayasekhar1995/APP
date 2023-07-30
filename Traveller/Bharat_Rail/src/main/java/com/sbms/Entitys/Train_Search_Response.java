package com.sbms.Entitys;

import javax.persistence.Embedded;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Train_Search_Response {

	private Integer train_no;
	private String train_name;
	private String from_date;
	private String arri_at_from_station;
	private String dept_at_from_station;
	private String to_date;
	private String arri_at_to_station;
	private String dept_at_to_station;
	private String total_journey_hours;
	private String total_distance;
	@XmlAttribute
	private String provider="Bharat Rail";
	@Embedded
	private Coach cost_for_each_Coach;
	@Embedded
	private Seat Available_seats_for_each_Coach;
	
}
