package com.alan.kardex.security.repository;

import org.springframework.stereotype.Repository;

import com.alan.kardex.repository.AbstractJpaDAO;
import com.alan.kardex.security.domain.User;

@Repository
public class UserDaoImpl extends AbstractJpaDAO<User> implements UserDao {
	
	public UserDaoImpl(){
		setClazz(User.class);
	}

	@Override
	public User find(String username) {
		return findOne(username);
	}
}
