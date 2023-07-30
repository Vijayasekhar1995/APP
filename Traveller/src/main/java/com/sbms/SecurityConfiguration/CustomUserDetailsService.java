package com.sbms.SecurityConfiguration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sbms.Entitys.User;
import com.sbms.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("Request came for the Authentication ");
		Optional<User> findById = userRepository.findById(username);
		if (findById.isPresent()) {
			System.out.println("User Object is :: " + findById.get());
			CustomUserDetails userDetails = null;
			userDetails = new CustomUserDetails();
			userDetails.setUser(findById.get());
			return userDetails;
		} else {
			throw new UsernameNotFoundException("User not exist with name : " + username);
		}

	}

}
