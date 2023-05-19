package com.ssafit.model.service;

import java.util.List;

import com.ssafit.exception.DuplicatedException;
import com.ssafit.model.dto.User;

public interface UserService {
	
	List<User> findAllUsers();
	
	User findUser(int idx);

	int addUser(User user) throws DuplicatedException;

	User login(String id, String password);

	int modifyUser(User user) throws DuplicatedException;

	int removeUser(int idx);

	boolean isIdExist(String id);

	boolean isEmailExist(String email);

	boolean isNicknameExist(String nickname);
}
