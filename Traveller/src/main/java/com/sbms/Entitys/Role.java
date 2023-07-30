package com.sbms.Entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Traveller_Role")
public class Role {
	
	@Id
	@GeneratedValue
	private int role_id;
	private String role;

}
