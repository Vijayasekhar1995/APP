package com.sbms.Entitys;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Component
public class SeatBookingResponse {

	private Integer id;
	private String name;
	private String gender;
	private Integer age;
	private String coach_no;
	private Integer seat_no;
	private String berth_type;
	private String status;
	

}
