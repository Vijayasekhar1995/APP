package com.sbms.Repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sbms.Entitys.Seating_Information;

public interface Seating_InformationRepository extends JpaRepository<Seating_Information, Integer> {

	@Query("from Seating_Information where train_no=:train_no and on_date=:on_date and quota=:quota and coach=:coach and isAllocated=:isAllocated")
	public List<Seating_Information> findByTrain_noAndOn_dateAndQuotaAndCoach(Integer train_no, String on_date,
			String quota, String coach, Integer isAllocated);
	@Query("select count(coach) from Seating_Information where train_no=:train_no and coach=:seat_type and on_date=:date and is_allocated=0")
	public Integer get_number_of_Seats_Available_In_EachTrain(Integer train_no, String seat_type, String date);
	
	@Query("select distinct(coach) from Seating_Information where train_no=:train_no")
	public List<String> get_Avaialble_Coaches(Integer train_no);
	

}
