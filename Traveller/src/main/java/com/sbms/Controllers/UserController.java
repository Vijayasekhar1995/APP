package com.sbms.Controllers;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbms.Entitys.Role;
import com.sbms.Entitys.User;
import com.sbms.ServicesI.UserServicesI;

@RestController
public class UserController {

	@Autowired
	UserServicesI userServicesI;

	@RequestMapping(method = RequestMethod.POST, value = "/user_reg", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		try {
			if(user.getUser_roles().size()==0){
				
			}
		} catch (NullPointerException e) {
			HashSet<Role> hashSet = new HashSet<>();
			Role role = new Role();
			role.setRole("CLIENT");
			hashSet.add(role);
			user.setUser_roles(hashSet);
		}
		
		String saveUser = userServicesI.saveUser(user);
		if (saveUser.equals("The User Registered Successfully")) {
			return new ResponseEntity<String>(saveUser, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>(saveUser,HttpStatus.BAD_REQUEST);
		}
	}
}
