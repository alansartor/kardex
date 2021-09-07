package com.alan.kardex.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alan.kardex.security.domain.User;
import com.alan.kardex.security.repository.UserDao;

@Service
@Transactional
public class ApplicationUserDetailsService implements UserDetailsService, UserSrv{
	@Autowired 
	private UserDao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = dao.find(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username");
		}
		return user;
	}

}