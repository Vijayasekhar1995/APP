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

import org.bouncycastle.jcajce.provider.symmetric.Threefish.KeyGen_512;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.sym.Name;
import com.sbms.Entitys.Passenger;
import com.sbms.Entitys.Seating_Information;
import com.sbms.Entitys.Seating_Information_InMemory;
import com.sbms.Entitys.TrainCoachInfo;
import com.sbms.FeignClients.FeignClientToTrainApplication;
import com.sbms.Repositorys.Seating_InformationRepository;
import com.sbms.ServicesI.InitialSeatingGenerationInMemoryServicesI;
import com.sbms.ServicesI.SeatingGenerationServiceI;

@Service
public class SeatingGenerationServiceImpl implements SeatingGenerationServiceI {

	@Autowired
	FeignClientToTrainApplication feignClientToTrainApplication;

	@Autowired
	Seating_InformationRepository seating_InformationRepository;

	@Override
	public List<Seating_Information> initialSeatingGeneration(List<TrainCoachInfo> trainCoachInfoList) {

		ArrayList<Seating_Information> list = new ArrayList<>();

		for (TrainCoachInfo trainCoachInfo : trainCoachInfoList) {

			for (int j = 0; j < 1; j++) {

				LocalDate localdate = LocalDate.now().plusDays(j);
				DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				String date = localdate.format(ofPattern);

				Integer get_GEN_ = trainCoachInfo.get_GEN_();
				for (int i = 1; i <= get_GEN_; i++) {

				}

//				Names of "2S" coach generation, Seat number generation in each "2S" coach and  Type of berth in each "2S" coach

				Integer get_2S_ = trainCoachInfo.get_2S_();
				for (int i = 1; i <= get_2S_; i++) {
					for (int k = 1; k <= 108; k++) {

						Seating_Information seating_Information = new Seating_Information();
						Passenger passenger = new Passenger();
						seating_Information.setTrain_no(trainCoachInfo.getTrain_no());
						seating_Information.setOn_date(date);
						passenger.setSeat_no(k);
						seating_Information.setCoach("2S");
						passenger.setCoach_no("D" + i);
						seating_Information.setIsAllocated(0);
						seating_Information.setQuota("GENERAL");
						if (k % 6 == 1 || k % 6 == 0) {
							passenger.setBerth_type("WINDOW");
						} else if (k % 12 == 3 || k % 12 == 4 || k % 12 == 9 || k % 12 == 10) {
							passenger.setBerth_type("AISLE");
						} else if (k % 12 == 2 || k % 12 == 5 || k % 12 == 8 || k % 12 == 11) {
							passenger.setBerth_type("MIDDLE");
						}
						seating_Information.setPassenger(passenger);
						list.add(seating_Information);
					}
				}

//				Names of "CC" coach generation, Seat number generation in each "CC" coach and  Type of berth in each "CC" coach

				Integer get_CC_ = trainCoachInfo.get_CC_();
				for (int i = 1; i <= get_CC_; i++) {

					for (int k = 1; k <= 73; k++) {

						if (k == 1 || k == 2 || k == 3 || k == 4 || k == 72 || k == 73) {
							Seating_Information seating_Information = new Seating_Information();
							Passenger passenger = new Passenger();
							seating_Information.setTrain_no(trainCoachInfo.getTrain_no());
							seating_Information.setOn_date(date);
							passenger.setSeat_no(k);
							seating_Information.setCoach("CC");
							passenger.setCoach_no("C" + i);
							seating_Information.setIsAllocated(0);
							seating_Information.setQuota("GENERAL");
							if (k == 1 || k == 4 || k == 73) {
								passenger.setBerth_type("WINDOW");
								list.add(seating_Information);
								continue;
							} else if (k == 2) {
								passenger.setBerth_type("MIDDLE");
								list.add(seating_Information);
								continue;
							}
							passenger.setBerth_type("AISLE");
							seating_Information.setPassenger(passenger);
							list.add(seating_Information);

						}

						Seating_Information seating_Information = new Seating_Information();
						Passenger passenger = new Passenger();
						seating_Information.setTrain_no(trainCoachInfo.getTrain_no());
						seating_Information.setOn_date(date);
						passenger.setSeat_no(k);
						seating_Information.setCoach("CC");
						passenger.setCoach_no("C" + i);
						seating_Information.setIsAllocated(0);
						seating_Information.setQuota("GENERAL");
						if (k % 5 == 0 || k % 5 == 4) {
							passenger.setBerth_type("WINDOW");
						} else if (k % 5 == 1) {
							passenger.setBerth_type("MIDDLE");
						} else if (k % 5 != 0 && k % 5 != 4 && k % 5 != 1) {
							passenger.setBerth_type("AISLE");
						}
						seating_Information.setPassenger(passenger);
						list.add(seating_Information);
					}

				}

//				Names of "SL" coach generation, Seat number generation in each "SL" coach and  Type of berth in each "SL" coach

				Integer get_SL_ = trainCoachInfo.get_SL_();
				for (int i = 1; i <= get_SL_; i++) {
					for (int k = 1; k <= 72; k++) {

						Seating_Information seating_Information = new Seating_Information();
						Passenger passenger = new Passenger();
						seating_Information.setTrain_no(trainCoachInfo.getTrain_no());
						seating_Information.setOn_date(date);
						passenger.setSeat_no(k);
						seating_Information.setCoach("SL");
						passenger.setCoach_no("S" + i);
						seating_Information.setIsAllocated(0);
						seating_Information.setQuota("GENERAL");

						if (k % 8 == 1 || k % 8 == 4) {
							passenger.setBerth_type("LOWER");
						} else if (k % 8 == 2 || k % 8 == 5) {
							passenger.setBerth_type("MIDDLE");
						} else if (k % 8 == 3 || k % 8 == 6) {
							passenger.setBerth_type("UPPER");
						} else if (k % 8 == 7) {
							passenger.setBerth_type("S.LOWER");
						} else if (k % 8 == 0) {
							passenger.setBerth_type("S.UPPER");
						}
						seating_Information.setPassenger(passenger);
						list.add(seating_Information);
					}
				}

//				Names of "3AC" coach generation, Seat number generation in each "3AC" coach and  Type of berth in each "3AC" coach

				Integer get_3AC_ = trainCoachInfo.get_3AC_();
				for (int i = 1; i <= get_3AC_; i++) {
					for (int k = 1; k <= 72; k++) {

						Seating_Information seating_Information = new Seating_Information();
						Passenger passenger = new Passenger();
						seating_Information.setTrain_no(trainCoachInfo.getTrain_no());
						seating_Information.setOn_date(date);
						passenger.setSeat_no(k);
						seating_Information.setCoach("3AC");
						passenger.setCoach_no("B" + i);
						seating_Information.setIsAllocated(0);
						seating_Information.setQuota("GENERAL");

						if (k % 8 == 1 || k % 8 == 4) {
							passenger.setBerth_type("LOWER");
						} else if (k % 8 == 2 || k % 8 == 5) {
							passenger.setBerth_type("MIDDLE");
						} else if (k % 8 == 3 || k % 8 == 6) {
							passenger.setBerth_type("UPPER");
						} else if (k % 8 == 7) {
							passenger.setBerth_type("S.LOWER");
						} else if (k % 8 == 0) {
							passenger.setBerth_type("S.UPPER");
						}
						seating_Information.setPassenger(passenger);
						list.add(seating_Information);
					}

				}

				Integer get_2AC_ = trainCoachInfo.get_2AC_();
				for (int i = 1; i <= get_2AC_; i++) {
					for (int k = 1; k <= 54; k++) {

						Seating_Information seating_Information = new Seating_Information();
						Passenger passenger = new Passenger();
						seating_Information.setTrain_no(trainCoachInfo.getTrain_no());
						seating_Information.setOn_date(date);
						passenger.setSeat_no(k);
						seating_Information.setCoach("2AC");
						passenger.setCoach_no("A" + i);
						seating_Information.setIsAllocated(0);
						seating_Information.setQuota("GENERAL");

						if (k % 6 == 1 || k % 6 == 3) {
							passenger.setBerth_type("LOWER");
						} else if (k % 6 == 0) {
							passenger.setBerth_type("S.UPPER");
						} else if (k % 2 == 0 || k % 4 == 0) {
							passenger.setBerth_type("UPPER");
						} else if (k % 6 == 5) {
							passenger.setBerth_type("S.LOWER");
						}
						seating_Information.setPassenger(passenger);
						list.add(seating_Information);
					}
				}

				Integer get_1AC_ = trainCoachInfo.get_1AC_();
				for (int i = 1; i <= get_1AC_; i++) {
					for (int k = 1; k <= 26; k++) {

						Seating_Information seating_Information = new Seating_Information();
						Passenger passenger = new Passenger();
						seating_Information.setTrain_no(trainCoachInfo.getTrain_no());
						seating_Information.setOn_date(date);
						passenger.setSeat_no(k);
						seating_Information.setCoach("1AC");
						passenger.setCoach_no("H" + i);
						seating_Information.setIsAllocated(0);
						seating_Information.setQuota("GENERAL");

						if (k % 2 == 1) {
							passenger.setBerth_type("LOWER");
						} else {
							passenger.setBerth_type("UPPER");
						}
						seating_Information.setPassenger(passenger);
						list.add(seating_Information);
					}
				}
			}
		}
		List<Seating_Information> saveAll = seating_InformationRepository.saveAll(list);
		return saveAll;
	}

	@Override
	public List<Seating_Information> dailySeatingGeneration(List<TrainCoachInfo> trainCoachInfoList) {

		ArrayList<Seating_Information> list = new ArrayList<>();

		for (TrainCoachInfo trainCoachInfo : trainCoachInfoList) {

			for (int j = 0; j < 1; j++) {

				LocalDate localdate = LocalDate.now().plusDays(j);
				DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				String date = localdate.format(ofPattern);

				Integer get_GEN_ = trainCoachInfo.get_GEN_();
				for (int i = 1; i <= get_GEN_; i++) {

				}

//				Names of "2S" coach generation, Seat number generation in each "2S" coach and  Type of berth in each "2S" coach

				Integer get_2S_ = trainCoachInfo.get_2S_();
				for (int i = 1; i <= get_2S_; i++) {
					for (int k = 1; k <= 108; k++) {

						Seating_Information seating_Information = new Seating_Information();
						Passenger passenger = new Passenger();
						seating_Information.setTrain_no(trainCoachInfo.getTrain_no());
						seating_Information.setOn_date(date);
						passenger.setSeat_no(k);
						seating_Information.setCoach("2S");
						passenger.setCoach_no("D" + i);
						seating_Information.setIsAllocated(0);
						seating_Information.setQuota("GENERAL");
						if (k % 6 == 1 || k % 6 == 0) {
							passenger.setBerth_type("WINDOW");
						} else if (k % 12 == 3 || k % 12 == 4 || k % 12 == 9 || k % 12 == 10) {
							passenger.setBerth_type("AISLE");
						} else if (k % 12 == 2 || k % 12 == 5 || k % 12 == 8 || k % 12 == 11) {
							passenger.setBerth_type("MIDDLE");
						}
						seating_Information.setPassenger(passenger);
						list.add(seating_Information);
					}
				}

//				Names of "CC" coach generation, Seat number generation in each "CC" coach and  Type of berth in each "CC" coach

				Integer get_CC_ = trainCoachInfo.get_CC_();
				for (int i = 1; i <= get_CC_; i++) {

					for (int k = 1; k <= 73; k++) {

						if (k == 1 || k == 2 || k == 3 || k == 4 || k == 72 || k == 73) {
							Seating_Information seating_Information = new Seating_Information();
							Passenger passenger = new Passenger();
							seating_Information.setTrain_no(trainCoachInfo.getTrain_no());
							seating_Information.setOn_date(date);
							passenger.setSeat_no(k);
							seating_Information.setCoach("CC");
							passenger.setCoach_no("C" + i);
							seating_Information.setIsAllocated(0);
							seating_Information.setQuota("GENERAL");
							if (k == 1 || k == 4 || k == 73) {
								passenger.setBerth_type("WINDOW");
								list.add(seating_Information);
								continue;
							} else if (k == 2) {
								passenger.setBerth_type("MIDDLE");
								list.add(seating_Information);
								continue;
							}
							passenger.setBerth_type("AISLE");
							seating_Information.setPassenger(passenger);
							list.add(seating_Information);

						}

						Seating_Information seating_Information = new Seating_Information();
						Passenger passenger = new Passenger();
						seating_Information.setTrain_no(trainCoachInfo.getTrain_no());
						seating_Information.setOn_date(date);
						passenger.setSeat_no(k);
						seating_Information.setCoach("CC");
						passenger.setCoach_no("C" + i);
						seating_Information.setIsAllocated(0);
						seating_Information.setQuota("GENERAL");
						if (k % 5 == 0 || k % 5 == 4) {
							passenger.setBerth_type("WINDOW");
						} else if (k % 5 == 1) {
							passenger.setBerth_type("MIDDLE");
						} else if (k % 5 != 0 && k % 5 != 4 && k % 5 != 1) {
							passenger.setBerth_type("AISLE");
						}
						seating_Information.setPassenger(passenger);
						list.add(seating_Information);
					}

				}

//				Names of "SL" coach generation, Seat number generation in each "SL" coach and  Type of berth in each "SL" coach

				Integer get_SL_ = trainCoachInfo.get_SL_();
				for (int i = 1; i <= get_SL_; i++) {
					for (int k = 1; k <= 72; k++) {

						Seating_Information seating_Information = new Seating_Information();
						Passenger passenger = new Passenger();
						seating_Information.setTrain_no(trainCoachInfo.getTrain_no());
						seating_Information.setOn_date(date);
						passenger.setSeat_no(k);
						seating_Information.setCoach("SL");
						passenger.setCoach_no("S" + i);
						seating_Information.setIsAllocated(0);
						seating_Information.setQuota("GENERAL");

						if (k % 8 == 1 || k % 8 == 4) {
							passenger.setBerth_type("LOWER");
						} else if (k % 8 == 2 || k % 8 == 5) {
							passenger.setBerth_type("MIDDLE");
						} else if (k % 8 == 3 || k % 8 == 6) {
							passenger.setBerth_type("UPPER");
						} else if (k % 8 == 7) {
							passenger.setBerth_type("S.LOWER");
						} else if (k % 8 == 0) {
							passenger.setBerth_type("S.UPPER");
						}
						seating_Information.setPassenger(passenger);
						list.add(seating_Information);
					}
				}

//				Names of "3AC" coach generation, Seat number generation in each "3AC" coach and  Type of berth in each "3AC" coach

				Integer get_3AC_ = trainCoachInfo.get_3AC_();
				for (int i = 1; i <= get_3AC_; i++) {
					for (int k = 1; k <= 72; k++) {

						Seating_Information seating_Information = new Seating_Information();
						Passenger passenger = new Passenger();
						seating_Information.setTrain_no(trainCoachInfo.getTrain_no());
						seating_Information.setOn_date(date);
						passenger.setSeat_no(k);
						seating_Information.setCoach("3AC");
						passenger.setCoach_no("B" + i);
						seating_Information.setIsAllocated(0);
						seating_Information.setQuota("GENERAL");

						if (k % 8 == 1 || k % 8 == 4) {
							passenger.setBerth_type("LOWER");
						} else if (k % 8 == 2 || k % 8 == 5) {
							passenger.setBerth_type("MIDDLE");
						} else if (k % 8 == 3 || k % 8 == 6) {
							passenger.setBerth_type("UPPER");
						} else if (k % 8 == 7) {
							passenger.setBerth_type("S.LOWER");
						} else if (k % 8 == 0) {
							passenger.setBerth_type("S.UPPER");
						}
						seating_Information.setPassenger(passenger);
						list.add(seating_Information);
					}

				}

				Integer get_2AC_ = trainCoachInfo.get_2AC_();
				for (int i = 1; i <= get_2AC_; i++) {
					for (int k = 1; k <= 54; k++) {

						Seating_Information seating_Information = new Seating_Information();
						Passenger passenger = new Passenger();
						seating_Information.setTrain_no(trainCoachInfo.getTrain_no());
						seating_Information.setOn_date(date);
						passenger.setSeat_no(k);
						seating_Information.setCoach("2AC");
						passenger.setCoach_no("A" + i);
						seating_Information.setIsAllocated(0);
						seating_Information.setQuota("GENERAL");

						if (k % 6 == 1 || k % 6 == 3) {
							passenger.setBerth_type("LOWER");
						} else if (k % 6 == 0) {
							passenger.setBerth_type("S.UPPER");
						} else if (k % 2 == 0 || k % 4 == 0) {
							passenger.setBerth_type("UPPER");
						} else if (k % 6 == 5) {
							passenger.setBerth_type("S.LOWER");
						}
						seating_Information.setPassenger(passenger);
						list.add(seating_Information);
					}
				}

				Integer get_1AC_ = trainCoachInfo.get_1AC_();
				for (int i = 1; i <= get_1AC_; i++) {
					for (int k = 1; k <= 26; k++) {

						Seating_Information seating_Information = new Seating_Information();
						Passenger passenger = new Passenger();
						seating_Information.setTrain_no(trainCoachInfo.getTrain_no());
						seating_Information.setOn_date(date);
						passenger.setSeat_no(k);
						seating_Information.setCoach("1AC");
						passenger.setCoach_no("H" + i);
						seating_Information.setIsAllocated(0);
						seating_Information.setQuota("GENERAL");

						if (k % 2 == 1) {
							passenger.setBerth_type("LOWER");
						} else {
							passenger.setBerth_type("UPPER");
						}
						seating_Information.setPassenger(passenger);
						list.add(seating_Information);
					}
				}
			}
		}
		List<Seating_Information> saveAll = seating_InformationRepository.saveAll(list);
		return saveAll;
	}

}
