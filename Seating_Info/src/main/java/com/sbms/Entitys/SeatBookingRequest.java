package com.sbms.Entitys;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SeatBookingRequest {
	
	@NonNull
	private Integer pnr;
	@NonNull
	private Integer train_no;
	@NonNull
	private String quota;
	@NonNull
	private String coach;
	@NonNull
	private String on_date;
	@NonNull
	private List<Passenger> passengers;
	
}
