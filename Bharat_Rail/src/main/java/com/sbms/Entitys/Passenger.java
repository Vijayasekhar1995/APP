package com.sbms.Entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Passenger {
	
	@Id
	@SequenceGenerator(name="logical_name",sequenceName = "Passenger_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logical_name")
	private Integer id;
	private String name;
	private String gender;
	private Integer age;
	private String berth_type;
	private String coach_no;
	private Integer seat_no;
}
