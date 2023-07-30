package com.sbms.ServicesI;

import java.util.List;

import com.sbms.Entitys.TrainCoachInfo;

public interface TrainServiceI {
	
	public List<Integer> getTrainNumbers();
	
	public List<TrainCoachInfo> coachInfoofTrains(List<Integer> train_nos_list);

}
