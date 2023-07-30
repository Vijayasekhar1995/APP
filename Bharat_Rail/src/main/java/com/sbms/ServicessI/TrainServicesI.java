package com.sbms.ServicessI;

import com.sbms.Entitys.Availabe_Trains;
import com.sbms.Entitys.Train_Search_Request;

public interface TrainServicesI {
	
	Availabe_Trains train_search(Train_Search_Request train_Search_Request);

}
