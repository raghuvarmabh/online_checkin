package com.rv.online_checkin;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.rv.online_checkin.models.User;



public class MyUserDetails implements org.springframework.security.core.userdetails.UserDetails{
	
	private String userName;
	private String password;
	private boolean active;
	private List<GrantedAuthority>  authorities;
	
	
	public MyUserDetails(User user) {
			this.userName = user.getUserName();
			this.password = user.getPassword();
			this.active = user.isEnabled();
			
			this.authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole()))
					.collect(Collectors.toList());
			System.out.println("test");
	}
	
	public MyUserDetails() {
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

	
}
