package com.reddy.jwt.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reddy.jwt.entity.user;
import com.reddy.jwt.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user userEntity = userRepo.findByUname(username);
		return new User(userEntity.getUname(), userEntity.getUpwd(), Collections.emptyList());

	}

	public boolean saveUser(user user) {
		user = userRepo.save(user);
		return user.getUserid() != null;

	}
}
