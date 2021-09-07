package com.alan.kardex.security.repository;

import com.alan.kardex.security.domain.User;

public interface UserDao {

	User find(String username);

}