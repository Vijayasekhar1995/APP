package com.sbms.Entitys;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class SeatingAvailabilityRequest {
	
	String date;
	List<Integer> list_of_trainnumbers;
	

}
