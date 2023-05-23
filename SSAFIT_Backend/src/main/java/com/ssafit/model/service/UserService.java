package com.ssafit.model.service;

import java.util.List;

import com.ssafit.exception.CustomException;
import com.ssafit.model.dto.User;

public interface UserService {
	
	String findId(User user);
	
	List<User> findAllUsers();
	
	User findUser(int idx);
	
	User confirmUser(User user);

	int addUser(User user) throws CustomException;

	User login(String id, String password) throws CustomException;

	int modifyUser(User user) throws CustomException;
	
	int modifyPassword(User user);

	int removeUser(int idx);

	boolean isIdExist(String id);

	boolean isEmailExist(String email);

	boolean isNicknameExist(String nickname);
}
