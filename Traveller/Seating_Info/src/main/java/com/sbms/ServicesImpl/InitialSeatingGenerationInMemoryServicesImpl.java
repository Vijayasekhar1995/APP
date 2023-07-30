package com.sbms.ServicesImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.sym.Name;
import com.sbms.Entitys.Seating_Information_InMemory;
import com.sbms.Entitys.TrainCoachInfo;
import com.sbms.FeignClients.FeignClientToTrainApplication;
import com.sbms.ServicesI.InitialSeatingGenerationInMemoryServicesI;

@Service
public class InitialSeatingGenerationInMemoryServicesImpl implements InitialSeatingGenerationInMemoryServicesI {

	@Autowired
	FeignClientToTrainApplication feignClientToTrainApplication;

	LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>>> all_trains_intial_seating = new LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>>>();

	LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>>> all_trains_daily_seating = new LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>>>();

	@Override
	public LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>>> initialSeatingGeneration(
			List<TrainCoachInfo> trainCoachInfoList) {

		for (TrainCoachInfo trainCoachInfo : trainCoachInfoList) {

			LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>> each_train_initialSeating = new LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>>();

			for (int j = 0; j < 10; j++) {

				LocalDate localdate = LocalDate.now().plusDays(j);
				DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				String localdate_in_String = localdate.format(ofPattern);
				String date_train_no_reference = trainCoachInfo.getTrain_no() + " " + localdate_in_String;

				LinkedHashMap<String, Seating_Information_InMemory[]> coaches = new LinkedHashMap<>();

				Integer get_GEN_ = trainCoachInfo.get_GEN_();
				for (int i = 1; i <= get_GEN_; i++) {
					coaches.put("G" + i, null);
				}

//				Names of "2S" coach generation, Seat number generation in each "2S" coach and  Type of berth in each "2S" coach

				Integer get_2S_ = trainCoachInfo.get_2S_();
				for (int i = 1; i <= get_2S_; i++) {
					Seating_Information_InMemory[] seating_Informations = new Seating_Information_InMemory[108];
					for (int k = 0; k < seating_Informations.length; k++) {
						seating_Informations[k] = new Seating_Information_InMemory();
						seating_Informations[k].setSeat_no(k + 1);
					}

					for (int k = 1; k <= seating_Informations.length; k++) {
						if (k % 6 == 0 || k % 6 == 1) {
							seating_Informations[k - 1].setBerth_type("WINDOW");
						}
						if (k % 3 == 0) {
							if (k % 6 != 0) {
								seating_Informations[k - 1].setBerth_type("AISLE");
								seating_Informations[k + 1 - 1].setBerth_type("AISLE");
							}
						}
					}

					for (int k = 1; k <= seating_Informations.length; k++) {
						if (seating_Informations[k - 1].getBerth_type() == null) {
							seating_Informations[k - 1].setBerth_type("MIDDLE");
						}
					}

					coaches.put("D" + i, seating_Informations);
				}

//				Names of "CC" coach generation, Seat number generation in each "CC" coach and  Type of berth in each "CC" coach

				Integer get_CC_ = trainCoachInfo.get_CC_();
				for (int i = 1; i <= get_CC_; i++) {
					Seating_Information_InMemory[] seating_Informations = new Seating_Information_InMemory[78];

					for (int k = 0; k < seating_Informations.length; k++) {
						seating_Informations[k] = new Seating_Information_InMemory();
						seating_Informations[k].setSeat_no(k + 1);
					}

					for (int k = 1; k <= seating_Informations.length; k++) {
						if (k == 1 || k == 4 || k == 75 || k == 78) {
							seating_Informations[k - 1].setBerth_type("WINDOW");
							continue;
						}
						if (k == 2 || k == 3 || k == 76 || k == 77) {
							seating_Informations[k - 1].setBerth_type("AISLE");
							continue;
						}
						if (k % 5 == 0) {
							seating_Informations[k - 1].setBerth_type("WINDOW");
							seating_Informations[k - 1 + 1].setBerth_type("MIDDLE");
							seating_Informations[k - 1 + 2].setBerth_type("AISLE");
							seating_Informations[k - 1 + 3].setBerth_type("AISLE");
							seating_Informations[k - 1 + 4].setBerth_type("WINDOW");
						}
					}

					coaches.put("C" + i, seating_Informations);
				}

//				Names of "SL" coach generation, Seat number generation in each "SL" coach and  Type of berth in each "SL" coach

				Integer get_SL_ = trainCoachInfo.get_SL_();
				for (int i = 1; i <= get_SL_; i++) {
					Seating_Information_InMemory[] seating_Informations = new Seating_Information_InMemory[72];

					for (int k = 0; k < seating_Informations.length; k++) {
						seating_Informations[k] = new Seating_Information_InMemory();
						seating_Informations[k].setSeat_no(k + 1);
					}

					for (int k = 1; k <= seating_Informations.length; k++) {
						if (k % 8 == 1) {
							seating_Informations[k - 1].setBerth_type("LOWER");
							seating_Informations[k - 1 + 3].setBerth_type("LOWER");
						}
						if (k % 8 == 2) {
							seating_Informations[k - 1].setBerth_type("MIDDLE");
							seating_Informations[k - 1 + 3].setBerth_type("MIDDLE");
						}
						if (k % 8 == 3) {
							seating_Informations[k - 1].setBerth_type("UPPER");
							seating_Informations[k - 1 + 3].setBerth_type("UPPER");
						}
						if (k % 8 == 7) {
							seating_Informations[k - 1].setBerth_type("S.LOWER");
							seating_Informations[k - 1 + 1].setBerth_type("S.UPPER");
						}

					}

					coaches.put("S" + i, seating_Informations);
				}

//				Names of "3AC" coach generation, Seat number generation in each "3AC" coach and  Type of berth in each "3AC" coach

				Integer get_3AC_ = trainCoachInfo.get_3AC_();
				for (int i = 1; i <= get_3AC_; i++) {
					Seating_Information_InMemory[] seating_Informations = new Seating_Information_InMemory[72];

					for (int k = 0; k < seating_Informations.length; k++) {
						seating_Informations[k] = new Seating_Information_InMemory();
						seating_Informations[k].setSeat_no(k + 1);
					}

					for (int k = 1; k <= seating_Informations.length; k++) {
						if (k % 8 == 1) {
							seating_Informations[k - 1].setBerth_type("LOWER");
							seating_Informations[k - 1 + 3].setBerth_type("LOWER");
						}
						if (k % 8 == 2) {
							seating_Informations[k - 1].setBerth_type("MIDDLE");
							seating_Informations[k - 1 + 3].setBerth_type("MIDDLE");
						}
						if (k % 8 == 3) {
							seating_Informations[k - 1].setBerth_type("UPPER");
							seating_Informations[k - 1 + 3].setBerth_type("UPPER");
						}
						if (k % 8 == 7) {
							seating_Informations[k - 1].setBerth_type("S.LOWER");
							seating_Informations[k - 1 + 1].setBerth_type("S.UPPER");
						}

					}

					coaches.put("B" + i, seating_Informations);
				}

				Integer get_2AC_ = trainCoachInfo.get_2AC_();
				for (int i = 1; i <= get_2AC_; i++) {
					Seating_Information_InMemory[] seating_Informations = new Seating_Information_InMemory[54];

					for (int k = 0; k < seating_Informations.length; k++) {
						seating_Informations[k] = new Seating_Information_InMemory();
						seating_Informations[k].setSeat_no(k + 1);
					}

					for (int k = 1; k <= seating_Informations.length; k++) {
						if (k % 6 == 1) {
							seating_Informations[k - 1].setBerth_type("LOWER");
							seating_Informations[k - 1 + 2].setBerth_type("LOWER");
						}
						if (k % 6 == 2) {
							seating_Informations[k - 1].setBerth_type("UPPER");
							seating_Informations[k - 1 + 2].setBerth_type("UPPER");
						}
						if (k % 6 == 5) {
							seating_Informations[k - 1].setBerth_type("S.LOWER");
							seating_Informations[k - 1 + 1].setBerth_type("S.UPPER");
						}

					}

					coaches.put("A" + i, seating_Informations);
				}

				Integer get_1AC_ = trainCoachInfo.get_1AC_();
				for (int i = 1; i <= get_1AC_; i++) {
					Seating_Information_InMemory[] seating_Informations = new Seating_Information_InMemory[26];

					for (int k = 0; k < seating_Informations.length; k++) {
						seating_Informations[k] = new Seating_Information_InMemory();
						seating_Informations[k].setSeat_no(k + 1);
					}
					for (int k = 1; k <= seating_Informations.length; k++) {
						if (k % 2 == 1) {
							seating_Informations[k - 1].setBerth_type("LOWER");
							seating_Informations[k - 1 + 1].setBerth_type("UPPER");
						}
					}

					coaches.put("H" + i, seating_Informations);
				}

				each_train_initialSeating.put(date_train_no_reference, coaches);

			}

			all_trains_intial_seating.put("" + trainCoachInfo.getTrain_no(), each_train_initialSeating);

		}

		return all_trains_intial_seating;

	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>>> dailySeatingGeneration(
			List<TrainCoachInfo> trainCoachInfoList) {
		for (TrainCoachInfo trainCoachInfo : trainCoachInfoList) {

			LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>> each_train_dailySeating = new LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>>();

			for (int j = 0; j < 1; j++) {

				LocalDate localdate = LocalDate.now().plusDays(10);
				DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				String localdate_in_String = localdate.format(ofPattern);
				String date_train_no_reference = trainCoachInfo.getTrain_no() + " " + localdate_in_String;

				LinkedHashMap<String, Seating_Information_InMemory[]> coaches = new LinkedHashMap<>();

				Integer get_GEN_ = trainCoachInfo.get_GEN_();
				for (int i = 1; i <= get_GEN_; i++) {
					coaches.put("G" + i, null);
				}

//				Names of "2S" coach generation, Seat number generation in each "2S" coach and  Type of berth in each "2S" coach

				Integer get_2S_ = trainCoachInfo.get_2S_();
				for (int i = 1; i <= get_2S_; i++) {
					Seating_Information_InMemory[] seating_Informations = new Seating_Information_InMemory[108];
					for (int k = 0; k < seating_Informations.length; k++) {
						seating_Informations[k] = new Seating_Information_InMemory();
						seating_Informations[k].setSeat_no(k + 1);
					}

					for (int k = 1; k <= seating_Informations.length; k++) {
						if (k % 6 == 0 || k % 6 == 1) {
							seating_Informations[k - 1].setBerth_type("WINDOW");
						}
						if (k % 3 == 0) {
							if (k % 6 != 0) {
								seating_Informations[k - 1].setBerth_type("AISLE");
								seating_Informations[k + 1 - 1].setBerth_type("AISLE");
							}
						}
					}

					for (int k = 1; k <= seating_Informations.length; k++) {
						if (seating_Informations[k - 1].getBerth_type() == null) {
							seating_Informations[k - 1].setBerth_type("MIDDLE");
						}
					}

					coaches.put("D" + i, seating_Informations);
				}

//				Names of "CC" coach generation, Seat number generation in each "CC" coach and  Type of berth in each "CC" coach

				Integer get_CC_ = trainCoachInfo.get_CC_();
				for (int i = 1; i <= get_CC_; i++) {
					Seating_Information_InMemory[] seating_Informations = new Seating_Information_InMemory[78];

					for (int k = 0; k < seating_Informations.length; k++) {
						seating_Informations[k] = new Seating_Information_InMemory();
						seating_Informations[k].setSeat_no(k + 1);
					}

					for (int k = 1; k <= seating_Informations.length; k++) {
						if (k == 1 || k == 4 || k == 75 || k == 78) {
							seating_Informations[k - 1].setBerth_type("WINDOW");
							continue;
						}
						if (k == 2 || k == 3 || k == 76 || k == 77) {
							seating_Informations[k - 1].setBerth_type("AISLE");
							continue;
						}
						if (k % 5 == 0) {
							seating_Informations[k - 1].setBerth_type("WINDOW");
							seating_Informations[k - 1 + 1].setBerth_type("MIDDLE");
							seating_Informations[k - 1 + 2].setBerth_type("AISLE");
							seating_Informations[k - 1 + 3].setBerth_type("AISLE");
							seating_Informations[k - 1 + 4].setBerth_type("WINDOW");
						}
					}

					coaches.put("C" + i, seating_Informations);
				}

//				Names of "SL" coach generation, Seat number generation in each "SL" coach and  Type of berth in each "SL" coach

				Integer get_SL_ = trainCoachInfo.get_SL_();
				for (int i = 1; i <= get_SL_; i++) {
					Seating_Information_InMemory[] seating_Informations = new Seating_Information_InMemory[72];

					for (int k = 0; k < seating_Informations.length; k++) {
						seating_Informations[k] = new Seating_Information_InMemory();
						seating_Informations[k].setSeat_no(k + 1);
					}

					for (int k = 1; k <= seating_Informations.length; k++) {
						if (k % 8 == 1) {
							seating_Informations[k - 1].setBerth_type("LOWER");
							seating_Informations[k - 1 + 3].setBerth_type("LOWER");
						}
						if (k % 8 == 2) {
							seating_Informations[k - 1].setBerth_type("MIDDLE");
							seating_Informations[k - 1 + 3].setBerth_type("MIDDLE");
						}
						if (k % 8 == 3) {
							seating_Informations[k - 1].setBerth_type("UPPER");
							seating_Informations[k - 1 + 3].setBerth_type("UPPER");
						}
						if (k % 8 == 7) {
							seating_Informations[k - 1].setBerth_type("S.LOWER");
							seating_Informations[k - 1 + 1].setBerth_type("S.UPPER");
						}

					}

					coaches.put("S" + i, seating_Informations);
				}

//				Names of "3AC" coach generation, Seat number generation in each "3AC" coach and  Type of berth in each "3AC" coach

				Integer get_3AC_ = trainCoachInfo.get_3AC_();
				for (int i = 1; i <= get_3AC_; i++) {
					Seating_Information_InMemory[] seating_Informations = new Seating_Information_InMemory[72];

					for (int k = 0; k < seating_Informations.length; k++) {
						seating_Informations[k] = new Seating_Information_InMemory();
						seating_Informations[k].setSeat_no(k + 1);
					}

					for (int k = 1; k <= seating_Informations.length; k++) {
						if (k % 8 == 1) {
							seating_Informations[k - 1].setBerth_type("LOWER");
							seating_Informations[k - 1 + 3].setBerth_type("LOWER");
						}
						if (k % 8 == 2) {
							seating_Informations[k - 1].setBerth_type("MIDDLE");
							seating_Informations[k - 1 + 3].setBerth_type("MIDDLE");
						}
						if (k % 8 == 3) {
							seating_Informations[k - 1].setBerth_type("UPPER");
							seating_Informations[k - 1 + 3].setBerth_type("UPPER");
						}
						if (k % 8 == 7) {
							seating_Informations[k - 1].setBerth_type("S.LOWER");
							seating_Informations[k - 1 + 1].setBerth_type("S.UPPER");
						}

					}

					coaches.put("B" + i, seating_Informations);
				}

				Integer get_2AC_ = trainCoachInfo.get_2AC_();
				for (int i = 1; i <= get_2AC_; i++) {
					Seating_Information_InMemory[] seating_Informations = new Seating_Information_InMemory[54];

					for (int k = 0; k < seating_Informations.length; k++) {
						seating_Informations[k] = new Seating_Information_InMemory();
						seating_Informations[k].setSeat_no(k + 1);
					}

					for (int k = 1; k <= seating_Informations.length; k++) {
						if (k % 6 == 1) {
							seating_Informations[k - 1].setBerth_type("LOWER");
							seating_Informations[k - 1 + 2].setBerth_type("LOWER");
						}
						if (k % 6 == 2) {
							seating_Informations[k - 1].setBerth_type("UPPER");
							seating_Informations[k - 1 + 2].setBerth_type("UPPER");
						}
						if (k % 6 == 5) {
							seating_Informations[k - 1].setBerth_type("S.LOWER");
							seating_Informations[k - 1 + 1].setBerth_type("S.UPPER");
						}

					}

					coaches.put("A" + i, seating_Informations);
				}

				Integer get_1AC_ = trainCoachInfo.get_1AC_();
				for (int i = 1; i <= get_1AC_; i++) {
					Seating_Information_InMemory[] seating_Informations = new Seating_Information_InMemory[26];

					for (int k = 0; k < seating_Informations.length; k++) {
						seating_Informations[k] = new Seating_Information_InMemory();
						seating_Informations[k].setSeat_no(k + 1);
					}
					for (int k = 1; k <= seating_Informations.length; k++) {
						if (k % 2 == 1) {
							seating_Informations[k - 1].setBerth_type("LOWER");
							seating_Informations[k - 1 + 1].setBerth_type("UPPER");
						}
					}

					coaches.put("H" + i, seating_Informations);
				}

				each_train_dailySeating.put(date_train_no_reference, coaches);

			}

			all_trains_daily_seating.put("" + trainCoachInfo.getTrain_no(), each_train_dailySeating);

		}

//		Inserting all trains daily seating of particular date in to the all trains intial seating.
		
		List<Integer> collect = trainCoachInfoList.stream().map(e -> e.getTrain_no()).collect(Collectors.toList());

		for (Integer integer : collect) {
			
			LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>> linkedHashMap2 = all_trains_daily_seating
					.get(integer+"");
			LocalDate localdate = LocalDate.now().plusDays(10);
			DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String localdate_in_String = localdate.format(ofPattern);
			String string = integer+" "+localdate_in_String;
			System.out.println("string is :: "+string);
			System.out.println(linkedHashMap2);
			LinkedHashMap<String, Seating_Information_InMemory[]> linkedHashMap = linkedHashMap2.get(string);
			
			LinkedHashMap<String, LinkedHashMap<String, Seating_Information_InMemory[]>> linkedHashMap1 = all_trains_intial_seating.get(integer+"");
			linkedHashMap1.put(string, linkedHashMap);
			all_trains_intial_seating.put(integer+"", linkedHashMap1);
			
		}

		return all_trains_intial_seating;

	}

}
