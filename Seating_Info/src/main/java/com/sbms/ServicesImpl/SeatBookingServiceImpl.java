package com.sbms.ServicesImpl;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbms.Entitys.Passenger;
import com.sbms.Entitys.SeatBookingRequest;
import com.sbms.Entitys.SeatBookingResponse;
import com.sbms.Entitys.Seating_Information;
import com.sbms.Exceptions.SeatsNotAvailableException;
import com.sbms.Repositorys.Seating_InformationRepository;
import com.sbms.ServicesI.SeatBookingServiceI;

import ch.qos.logback.core.joran.conditional.IfAction;
import net.bytebuddy.description.ModifierReviewable.OfAbstraction;

@Service
public class SeatBookingServiceImpl implements SeatBookingServiceI {

	@Autowired
	Seating_InformationRepository seating_InformationRepository;

	@Autowired
	SeatBookingResponse seatBookingResponse;

	@Override
	public List<Passenger> seatBooking(SeatBookingRequest seatBookingRequest) throws JsonProcessingException {


		ObjectMapper objectMapper = new ObjectMapper();

//      Getting list of empty seats in that particular train on that particular date on that particular Quota on that particular coach
		List<Seating_Information> empty_seats = seating_InformationRepository
				.findByTrain_noAndOn_dateAndQuotaAndCoach(seatBookingRequest.getTrain_no(),
						seatBookingRequest.getOn_date(), seatBookingRequest.getQuota(), seatBookingRequest.getCoach(),
						0)
				.stream().sorted().collect(Collectors.toList());

		System.out.println("empty seats :: " + objectMapper.writeValueAsString(empty_seats));

//		Getting list of coach no's in which seats are available
		List<String> empty_seat_coach_nos = empty_seats.stream().map(e -> e.getPassenger().getCoach_no()).distinct()
				.collect(Collectors.toList());

		System.out.println("list of coach no's in which seats are available is :: "
				+ objectMapper.writeValueAsString(empty_seat_coach_nos));

//		TreeMap<coach_no, empty seat in  that coach > i,e TreeMap represents no of empty seats in each coach
		LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
		TreeMap<Integer, String> treeMap = new TreeMap<>(Collections.reverseOrder());

		empty_seat_coach_nos.forEach(e1 -> {

//			Getting no.of empty seats in the each coach
			long count = empty_seats.stream().filter(e2 -> e2.getPassenger().getCoach_no().equals(e1)).count();
			System.out.println("The empty seats in the " + e1 + " coach is :: " + (int) count);
			linkedHashMap.put((int) count, e1);
			treeMap.put((int) count, e1);

		}

		);

		if (treeMap.size()==0) {
			throw new SeatsNotAvailableException("Seats are Not Available");
		}
		
		
		
//		Checking no of seats which came for booking are available in the coach_no which is having more empty seats. If seats are available in that coach then book in the that coach only.
		else if (treeMap.firstKey() >= seatBookingRequest.getPassengers().size()) {
//			Getting all empty seats information which are available in the coach_no which is having more empty seats.
			List<Seating_Information> seats_in_first_value = empty_seats.stream()
					.sorted((e1, e2) -> (e1.getPassenger().getSeat_no() < e2.getPassenger().getSeat_no()) ? 1
							: (e1.getPassenger().getSeat_no() > e2.getPassenger().getSeat_no()) ? -1 : 0)
					.filter(seat -> seat.getPassenger().getCoach_no().equals(treeMap.firstEntry().getValue()))
					.collect(Collectors.toList());

			System.out.println("seats_in_first_value :: " + objectMapper.writeValueAsString(seats_in_first_value));

//			seatBookingRequest.getPassengers().forEach(passenger -> {
//
//				seats_in_first_value.forEach(seat -> {
//
//					if (seat.getPassenger().getBerth_type().equals(passenger.getBerth_type())) {			
//
//						seat.getPassenger().setPassenger_id(passenger.getPassenger_id());
//						seat.getPassenger().setName(passenger.getName());
//						seat.getPassenger().setGender(passenger.getGender());
//						seat.getPassenger().setAge(passenger.getAge());
//						seat.getPassenger().setBerth_type(seat.getPassenger().getBerth_type());
//						seat.setIsAllocated(1);
//						seat.setPnr(seatBookingRequest.getPnr());
//						
//						Seating_Information save = seating_InformationRepository.save(seat);
//						
//						passengerslist.add(save.getPassenger());
//						
//					}
//					
//
//				}
//
//				);
//			}
//
//			);
			
			
//			This for loop belongs to book the tickets where expected berths of passengers are available
			for (int i = 0; i < seatBookingRequest.getPassengers().size(); i++) {

				for (int j = 0; j < seats_in_first_value.size(); j++) {
					if (seatBookingRequest.getPassengers().get(i).getBerth_type()
							.equals(seats_in_first_value.get(j).getPassenger().getBerth_type())
							&& seats_in_first_value.get(j).getIsAllocated() == 0
							&& seatBookingRequest.getPassengers().get(i).getCoach_no() == null
							&& seatBookingRequest.getPassengers().get(i).getSeat_no() == null) {

						seats_in_first_value.get(j).getPassenger()
								.setPassenger_id(seatBookingRequest.getPassengers().get(i).getPassenger_id());
						seats_in_first_value.get(j).getPassenger()
								.setName(seatBookingRequest.getPassengers().get(i).getName());
						seats_in_first_value.get(j).getPassenger()
								.setGender(seatBookingRequest.getPassengers().get(i).getGender());
						seats_in_first_value.get(j).getPassenger()
								.setAge(seatBookingRequest.getPassengers().get(i).getAge());
						seatBookingRequest.getPassengers().get(i)
								.setBerth_type(seats_in_first_value.get(j).getPassenger().getBerth_type());
						seats_in_first_value.get(j).setIsAllocated(1);
						seats_in_first_value.get(j).setPnr(seatBookingRequest.getPnr());

						Seating_Information save = seating_InformationRepository.save(seats_in_first_value.get(j));

						seatBookingRequest.getPassengers().remove(i);
						seatBookingRequest.getPassengers().add(i, save.getPassenger());

						break;

					} 
				}
			}
			
//			Passengers list where expected berths are not available
			List<Passenger> p_l_w_e_b_n_l = seatBookingRequest.getPassengers().stream()
					.filter(passenger -> passenger.getCoach_no() == null && passenger.getSeat_no() == null)
					.collect(Collectors.toList());
			
			System.out.println("List of Passengers where expected berths are not available :: "+p_l_w_e_b_n_l);

//			This for loop belongs to book the tickets where expected berths of passengers are not available
			for (int i = 0; i < seatBookingRequest.getPassengers().size(); i++) {

				for (int j = 0; j < seats_in_first_value.size(); j++) {
					if (seats_in_first_value.get(j).getIsAllocated() == 0
							&& seatBookingRequest.getPassengers().get(i).getCoach_no() == null
							&& seatBookingRequest.getPassengers().get(i).getSeat_no() == null) {

						seats_in_first_value.get(j).getPassenger()
								.setPassenger_id(seatBookingRequest.getPassengers().get(i).getPassenger_id());
						seats_in_first_value.get(j).getPassenger()
								.setName(seatBookingRequest.getPassengers().get(i).getName());
						seats_in_first_value.get(j).getPassenger()
								.setGender(seatBookingRequest.getPassengers().get(i).getGender());
						seats_in_first_value.get(j).getPassenger()
								.setAge(seatBookingRequest.getPassengers().get(i).getAge());
						seatBookingRequest.getPassengers().get(i)
								.setBerth_type(seats_in_first_value.get(j).getPassenger().getBerth_type());
						seats_in_first_value.get(j).setIsAllocated(1);
						seats_in_first_value.get(j).setPnr(seatBookingRequest.getPnr());

						Seating_Information save = seating_InformationRepository.save(seats_in_first_value.get(j));

						seatBookingRequest.getPassengers().remove(i);
						seatBookingRequest.getPassengers().add(i, save.getPassenger());

						break;

					}
				}
			}
		}
		
//		If no of seats which came for booking are not available in the coach_no which is having more empty seats then book all the seats, in all the coaches where seats are available.
		else if (true) {
//			Getting all empty seats information which are available in the coach_no which is having more empty seats.
			List<Seating_Information> all_seats = empty_seats.stream()
					.sorted((e1, e2) -> (e1.getPassenger().getSeat_no() < e2.getPassenger().getSeat_no()) ? 1
							: (e1.getPassenger().getSeat_no() > e2.getPassenger().getSeat_no()) ? -1 : 0)
					.sorted().collect(Collectors.toList());

			System.out.println("seats_in_first_value :: " + objectMapper.writeValueAsString(all_seats));	
			
//			This for loop belongs to book the tickets where expected berths of passengers are available
			for (int i = 0; i < seatBookingRequest.getPassengers().size(); i++) {

				for (int j = 0; j < all_seats.size(); j++) {
					if (seatBookingRequest.getPassengers().get(i).getBerth_type()
							.equals(all_seats.get(j).getPassenger().getBerth_type())
							&& all_seats.get(j).getIsAllocated() == 0
							&& seatBookingRequest.getPassengers().get(i).getCoach_no() == null
							&& seatBookingRequest.getPassengers().get(i).getSeat_no() == null) {

						all_seats.get(j).getPassenger()
								.setPassenger_id(seatBookingRequest.getPassengers().get(i).getPassenger_id());
						all_seats.get(j).getPassenger()
								.setName(seatBookingRequest.getPassengers().get(i).getName());
						all_seats.get(j).getPassenger()
								.setGender(seatBookingRequest.getPassengers().get(i).getGender());
						all_seats.get(j).getPassenger()
								.setAge(seatBookingRequest.getPassengers().get(i).getAge());
						seatBookingRequest.getPassengers().get(i)
								.setBerth_type(all_seats.get(j).getPassenger().getBerth_type());
						all_seats.get(j).setIsAllocated(1);
						all_seats.get(j).setPnr(seatBookingRequest.getPnr());

						Seating_Information save = seating_InformationRepository.save(all_seats.get(j));

						seatBookingRequest.getPassengers().remove(i);
						seatBookingRequest.getPassengers().add(i, save.getPassenger());

						break;

					} 
				}
			}
			
//			Passengers list where expected berths are not available
			List<Passenger> p_l_w_e_b_n_l = seatBookingRequest.getPassengers().stream()
					.filter(passenger -> passenger.getCoach_no() == null && passenger.getSeat_no() == null)
					.collect(Collectors.toList());
			
			System.out.println("List of Passengers where expected berths are not available :: "+p_l_w_e_b_n_l);
			
//			This for loop belongs to book the tickets where expected berths of passengers are not available
			for (int i = 0; i < seatBookingRequest.getPassengers().size(); i++) {

				for (int j = 0; j < all_seats.size(); j++) {
					if (all_seats.get(j).getIsAllocated() == 0
							&& seatBookingRequest.getPassengers().get(i).getCoach_no() == null
							&& seatBookingRequest.getPassengers().get(i).getSeat_no() == null) {

						all_seats.get(j).getPassenger()
								.setPassenger_id(seatBookingRequest.getPassengers().get(i).getPassenger_id());
						all_seats.get(j).getPassenger()
								.setName(seatBookingRequest.getPassengers().get(i).getName());
						all_seats.get(j).getPassenger()
								.setGender(seatBookingRequest.getPassengers().get(i).getGender());
						all_seats.get(j).getPassenger()
								.setAge(seatBookingRequest.getPassengers().get(i).getAge());
						seatBookingRequest.getPassengers().get(i)
								.setBerth_type(all_seats.get(j).getPassenger().getBerth_type());
						all_seats.get(j).setIsAllocated(1);
						all_seats.get(j).setPnr(seatBookingRequest.getPnr());

						Seating_Information save = seating_InformationRepository.save(all_seats.get(j));

						seatBookingRequest.getPassengers().remove(i);
						seatBookingRequest.getPassengers().add(i, save.getPassenger());

						break;

					}
				}
			}
		}
		return seatBookingRequest.getPassengers();
	}

}
