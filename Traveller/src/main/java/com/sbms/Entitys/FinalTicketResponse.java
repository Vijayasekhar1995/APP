package com.sbms.Entitys;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "Traveller_Ticket")
@Component
public class FinalTicketResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer pnr;
	@NonNull
	private String booked_on;
	@NonNull
	private String last_updated_on;
	@NonNull
	private Integer train_no;
	@NonNull
	private String train_name;
	@NonNull
	private String from_station;
	@NonNull
	private String from_date;
	@NonNull
	private String arri_at_from_station;
	@NonNull
	private String dept_at_from_station;
	@NonNull
	private String boarding_station;
	@NonNull
	private String boarding_date;
	@NonNull
	private String arri_at_boarding_station;
	@NonNull
	private String dept_at_boarding_station;
	@NonNull
	private String to_station;
	@NonNull
	private String arri_at_to_station;
	@NonNull
	private String dept_at_to_station;
	@NonNull
	private String to_date;
	@NonNull
	private String total_J_H_from_from_station;
	@NonNull
	private String total_J_H_from_boarding_station;
	@NonNull
	private String total_dist_from_from_station;
	@NonNull
	private String total_dist_from_boarding_station;
	@NonNull
	private Double cost;
	@NonNull
	private String quota;
	@NonNull
	private String coach;
	@NonNull
	@XmlAttribute
	private String provider;
	@NonNull
	@Embedded
	private Address address;
	@NonNull
	@OneToMany(cascade = CascadeType.ALL)
	private List<Passenger> passengers;
	
}
