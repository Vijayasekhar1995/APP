package com.sbms.Entitys;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Embeddable
public class Passenger {

	private Integer passenger_id;
	private String name;
	private String gender;
	private Integer age;
	private String coach_no;
	private Integer seat_no;
	@NonNull
	private String berth_type;

}
