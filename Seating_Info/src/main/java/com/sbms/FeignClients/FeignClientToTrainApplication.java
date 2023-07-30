package com.sbms.FeignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sbms.Entitys.TrainCoachInfo;

@FeignClient(name="Train-App")
public interface FeignClientToTrainApplication {
	
	@GetMapping("/trainNumbers")
	List<Integer> getTrainNumbers();
	
	@PostMapping("/coachInfoofTrains")
	List<TrainCoachInfo> coachInfoofTrains(List<Integer> train_nos_list);
	

}
