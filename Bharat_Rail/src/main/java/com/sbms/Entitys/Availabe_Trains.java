package com.sbms.Entitys;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class Availabe_Trains {

	List<Train_Search_Response> Available_Trains_List;

}
