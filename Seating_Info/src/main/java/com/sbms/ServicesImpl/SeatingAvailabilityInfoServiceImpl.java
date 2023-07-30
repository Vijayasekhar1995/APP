package com.sbms.ServicesImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbms.Entitys.Seat;
import com.sbms.Entitys.Seating_Information;
import com.sbms.Entitys.TrainCoachInfo;
import com.sbms.Repositorys.Seating_InformationRepository;
import com.sbms.ServicesI.SeatingAvailabilityInfoServiceI;

@Service
public class SeatingAvailabilityInfoServiceImpl implements SeatingAvailabilityInfoServiceI {

	@Autowired
	Seating_InformationRepository seating_InformationRepository;

	public HashMap<Integer, Seat> get_number_of_Seats_Available_In_EachTrain(List<Integer> list, String date) {

		HashMap<Integer, Seat> hashMap = new HashMap<>();

		list.stream().forEach(train_no -> {
			Seat seat = new Seat();
			List<String> coaches = seating_InformationRepository.get_Avaialble_Coaches(train_no);
			coaches.stream().forEach(coach -> {
				Integer integer = seating_InformationRepository.get_number_of_Seats_Available_In_EachTrain(train_no,
						coach, date);
				if (coach.equals("GEN")) {
					seat.set_GEN_(integer + "");
				} else if (coach.equals("2S")) {
					seat.set_2S_(integer + "");
				} else if (coach.equals("CC")) {
					seat.set_CC_(integer + "");
				} else if (coach.equals("SL")) {
					seat.set_SL_(integer + "");
				} else if (coach.equals("3AC")) {
					seat.set_3AC_(integer + "");
				} else if (coach.equals("2AC")) {
					seat.set_2AC_(integer + "");
				} else if (coach.equals("1AC")) {
					seat.set_1AC_(integer + "");
				}

			});
			hashMap.put(train_no, seat);
		});

		return hashMap;

	}

}
