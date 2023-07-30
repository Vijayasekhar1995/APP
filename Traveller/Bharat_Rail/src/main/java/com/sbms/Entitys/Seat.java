package com.sbms.Entitys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@XmlRootElement
@Embeddable
public class Seat {

	private String _GEN_ = "----";
	private String _2S_ = "----";
	private String _CC_ = "----";
	private String _SL_ = "----";
	private String _3AC_ = "----";
	private String _2AC_ = "----";
	private String _1AC_ = "----";

}
