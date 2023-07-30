package com.sbms.ServicesI;

import com.sbms.Entitys.Availabe_Trains;

public interface Train_ServicesI {
	
	public Availabe_Trains train_search(String from_station, String to_station, String date);
	
	

}
