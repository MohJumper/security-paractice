package com.mypage.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mypage.domain.User;
import com.mypage.respositories.UserRepository;
import com.mypage.security.CustomSecurityUser;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	
	// loadUserByUsername << method inside UserDetailsService
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Username or password is incorrect! ");
		}
		// how to handle this? 
		return new CustomSecurityUser(user);
	}

}
