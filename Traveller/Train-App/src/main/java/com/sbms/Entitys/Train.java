package com.sbms.Entitys;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@XmlRootElement
@Entity
@NoArgsConstructor
@Table(name="Train")
public class Train implements Serializable,Comparable<Train> {
	
	private static final long serialVersionUID = 1L;
	@SequenceGenerator(name="logical_name", sequenceName = "train_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logical_name")
	@Id
	private Integer id;
	private Integer train_no;
	private String train_name;
	private String station;
	private String coach;
	private Double amount;
	private String arrival;
	private String departure;
	private Integer day;
	private Integer km;
	@Override
	public int compareTo(Train train) {
		return this.getCoach().compareTo(train.getCoach());
	
	}
}
