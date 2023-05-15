package com.ssafit.model.service;

import java.util.List;

import com.ssafit.exception.DuplicatedException;
import com.ssafit.model.dto.User;

public interface UserService {
	
	List<User> getUserList();
	
	User getUser(String id);

	int signUp(User user) throws DuplicatedException;

	User login(String id, String password);

	int modify(User user) throws DuplicatedException;

	int withdraw(String id);

	boolean isIdExist(String id);

	boolean isEmailExist(String email);

	boolean isNicknameExist(String nickname);
}
