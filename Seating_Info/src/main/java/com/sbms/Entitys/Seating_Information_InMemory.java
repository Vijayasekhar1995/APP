package com.sbms.Entitys;

import javax.persistence.Embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seating_Information_InMemory {
	
	private Integer pnr;
	private Integer seat_no;
	private String berth_type;
	private Integer isAllocated=0;
	@Embedded
	private Passenger passenger;

}
