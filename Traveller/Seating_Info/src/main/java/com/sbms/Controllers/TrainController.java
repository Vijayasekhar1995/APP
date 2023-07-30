package com.sbms.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbms.Entitys.TrainCoachInfo;
import com.sbms.ServicesI.TrainServiceI;

@RestController
public class TrainController {
	
	@Autowired
	TrainServiceI trainServiceI;
	
	@GetMapping("/trainNumbers")
	public List<Integer> getTrainNumbers(){
		List<Integer> trainNumbers = trainServiceI.getTrainNumbers();
		return trainNumbers;
	}
	
	@PostMapping("/coachInfoofTrains")
	public ResponseEntity<List<TrainCoachInfo>> coachInfoofTrains(@RequestBody List<Integer> train_nos_list){
		List<TrainCoachInfo> coachInfoofTrains = trainServiceI.coachInfoofTrains(train_nos_list);
		return new ResponseEntity<List<TrainCoachInfo>>(coachInfoofTrains,HttpStatus.OK);
	}

}
