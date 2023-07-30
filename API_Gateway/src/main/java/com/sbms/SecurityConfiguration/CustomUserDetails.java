package com.sbms.SecurityConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sbms.Entitys.Role;
import com.sbms.Entitys.User;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getUser_roles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" +role.getRole()));
        }
        return authorities;

	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getUser_pwd();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUser_name();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
