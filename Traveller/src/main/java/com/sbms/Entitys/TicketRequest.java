package com.sbms.Entitys;

import java.util.List;

import javax.persistence.Embedded;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class TicketRequest {
	
	@NonNull
	private Integer train_no;
	@NonNull
	private String from_station;
	@NonNull
	private String to_station;
	@NonNull
	private String quota;
	@NonNull
	private String coach;
	@NonNull
	private String date;
	@NonNull
	@Embedded
	private Address address;
	@NonNull
	private List<Passenger> passengers;
	
}
