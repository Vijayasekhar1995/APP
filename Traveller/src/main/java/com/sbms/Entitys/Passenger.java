package com.sbms.Entitys;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Traveller_Passenger")
public class Passenger {
	@Id
//	@SequenceGenerator(name = "logical_name", sequenceName = "Traveller_Passenger_seq")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logical_name")
	private Integer id;
	private String name;
	private String gender;
	private Integer age;
	private String berth_type;
	private String coach_no;
	private Integer seat_no;
	
}
