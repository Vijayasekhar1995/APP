package com.sbms.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbms.Entitys.TrainCoachInfo;
import com.sbms.FeignClients.FeignClientToTrainApplication;
import com.sbms.ServicesI.TrainServiceI;

@Service
public class TrainServiceImpl implements TrainServiceI {
	
	@Autowired
	FeignClientToTrainApplication feignClientToTrainApplication;

	@Override
	public List<Integer> getTrainNumbers() {
		List<Integer> trainNumbers = feignClientToTrainApplication.getTrainNumbers();
		return trainNumbers;
	}

	@Override
	public List<TrainCoachInfo> coachInfoofTrains(List<Integer> train_nos_list) {
		List<TrainCoachInfo> coachInfoofTrains = feignClientToTrainApplication.coachInfoofTrains(train_nos_list);
		return coachInfoofTrains;
		
	}

}
