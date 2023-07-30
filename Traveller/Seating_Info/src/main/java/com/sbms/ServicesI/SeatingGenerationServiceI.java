package com.sbms.ServicesI;

import java.util.List;

import com.sbms.Entitys.Seating_Information;
import com.sbms.Entitys.TrainCoachInfo;

public interface SeatingGenerationServiceI {
	
	public List<Seating_Information> initialSeatingGeneration(List<TrainCoachInfo> trainCoachInfoList);

	public List<Seating_Information> dailySeatingGeneration(List<TrainCoachInfo> trainCoachInfoList);

}
