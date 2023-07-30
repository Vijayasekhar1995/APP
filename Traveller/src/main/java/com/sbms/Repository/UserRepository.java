package com.sbms.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sbms.Entitys.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	@Query(value = "select user_name from Traveller_User_dtls", nativeQuery = true)
	public List<String> getAlluser_names();
	
	@Query(value = "select user_email from Traveller_User_dtls", nativeQuery = true)
	public List<String> getAlluser_emails();
	

}
