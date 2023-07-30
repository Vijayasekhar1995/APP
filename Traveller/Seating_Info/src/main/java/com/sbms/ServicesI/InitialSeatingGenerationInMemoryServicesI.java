package com.sbms.ServicesI;

import java.util.LinkedHashMap;
import java.util.List;

import com.sbms.Entitys.Seating_Information_InMemory;
import com.sbms.Entitys.TrainCoachInfo;

public interface InitialSeatingGenerationInMemoryServicesI {
	
	public LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>>> initialSeatingGeneration(List<TrainCoachInfo> trainCoachInfoList);

	public LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>>> dailySeatingGeneration(List<TrainCoachInfo> trainCoachInfoList);

}
