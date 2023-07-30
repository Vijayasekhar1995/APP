package com.sbms.Entitys;

import java.time.LocalDate;

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
public class TicketRequest {
	
	private Integer train_no;
	private String from_station;
	private String to_station;
	private String coach;
	private String date;

}
