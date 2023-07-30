package com.sbms.Entitys;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor 
@Component
public class Updated_Booking_infojson {
	
	private Integer train_no;
	private String from_station;
	private String to_station;
	private String coach;
	private String date;
	private String boarding_station;

}
