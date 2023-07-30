package com.sbms.Repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sbms.Entitys.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	@Query(value = "select user_name from Gateway_User_dtls", nativeQuery = true)
	public List<String> getAlluser_names();
	
	@Query(value = "select user_email from Gateway_User_dtls", nativeQuery = true)
	public List<String> getAlluser_emails();
	
//	@Query(value = "select * from IRCTC_User_dtls where user_name=:user_name", nativeQuery = true)
//	public User findByUser_name(String user_name);

//	List<User> findByUser_name(String user_name);
}
