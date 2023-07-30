package com.sbms.Entitys;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Train_Search_Request {
	private String from_station;
	private String to_station;
	private String date; 
}
