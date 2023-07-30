package com.sbms.Entitys;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Embeddable
@Component
public class Coach {

	private String _GEN_ = "Not Available";
	private String _2S_ = "Not Available";
	private String _CC_ = "Not Available";
	private String _SL_ = "Not Available";
	private String _3AC_ = "Not Available";
	private String _2AC_ = "Not Available";
	private String _1AC_ = "Not Available";
	
	
}
