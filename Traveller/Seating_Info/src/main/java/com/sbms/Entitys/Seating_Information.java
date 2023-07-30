package com.sbms.Entitys;

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
@Table(name = "Seating_Information")
public class Seating_Information implements Comparable<Seating_Information> {

	@SequenceGenerator(name = "logical_name", sequenceName = "Seating_Information_Sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logical_name")
	@Id
	private Integer id;
	private Integer train_no;
	private String on_date;
	private String coach;
	private Integer isAllocated = 0;
	@Embedded
	private Passenger passenger;
	private Integer pnr;
	private String quota = Quota.General.toString();

	@Override
	public int compareTo(Seating_Information seating_Information) {
		return this.getPassenger().getCoach_no().compareTo(seating_Information.getPassenger().getCoach_no());
	}

}
