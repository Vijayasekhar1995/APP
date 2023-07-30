package com.sbms.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sbms.Entitys.User;
import com.sbms.Repository.UserRepository;
import com.sbms.ServicesI.UserServicesI;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServicesImpl implements UserServicesI {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public String saveUser(User user) {
	
		List<String> alluser_names = userRepository.getAlluser_names();
		for (String string : alluser_names) {
			if (string.equals(user.getUser_name())) {
				return "The UserName :: " + user.getUser_name() + " is already Existed !!.. So, Please register with Another UserName ";
			}
		}
		
		List<String> alluser_emails = userRepository.getAlluser_emails();
		
		for (String string : alluser_emails) {
			if (string.equals(user.getUser_email())) {
				return "The UserEmail :: " + user.getUser_email() + " is already Registered !!.. So, Please register with Another Email  ";
			}
		}
		
		String encode = passwordEncoder.encode(user.getUser_pwd());
		user.setUser_pwd(encode);
		userRepository.save(user);
		return "The User Registered Successfully";
	}

}
