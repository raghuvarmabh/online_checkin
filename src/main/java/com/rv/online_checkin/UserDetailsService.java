package com.rv.online_checkin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rv.online_checkin.models.User;
import com.rv.online_checkin.models.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(userName);
		
		user.orElseThrow(() -> new UsernameNotFoundException("user not found exception " + userName));
		return user.map(MyUserDetails::new).get();
	}
	}
