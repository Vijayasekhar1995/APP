package com.sbms.ServicesI;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sbms.Entitys.Train;
import com.sbms.Entitys.TrainCoachInfo;
import com.sbms.Entitys.Train_Search_Request;
import com.sbms.Entitys.Train_Search_Response;

public interface TrainServiceI {
	
	public Train save(Train trian);
	
	public List<Train> findAll();
	
	public List<Train> saveAll(List<Train> trainList);
	
	public Optional<Train> findById(Integer id);
	
	public List<Train_Search_Response> trains_Search(Train_Search_Request train_Search_Request) throws JsonProcessingException;
	
	public List<Integer> getTrainNumbers();
	
	public List<TrainCoachInfo> coachInfoofTrains(List<Integer> train_no_list);

}
