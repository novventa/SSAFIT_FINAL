package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.exception.CustomException;
import com.ssafit.model.dao.UserDao;
import com.ssafit.model.dto.ErrorCode;
import com.ssafit.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public List<User> findAllUsers() {
		return userDao.selectAll();
	}

	@Override
	public String findId(User user) {
		return userDao.selectId(user);
	}
	
	@Override
	public User findUser(int idx) {
		return userDao.selectUserByIdx(idx);
	}
	
	@Override
	public int addUser(User user) throws CustomException {
		if(isIdExist(user.getId())) {
			throw new CustomException(ErrorCode.DUPLICATED_ID);
		}
		if(isEmailExist(user.getEmail())) {
			throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
		}
		if(isNicknameExist(user.getNickname())) {
			throw new CustomException(ErrorCode.DUPLICATED_NICKNAME);
		}
		return userDao.insertUser(user);
	}

	@Override
	public User login(String id, String password) throws CustomException {
		User user = userDao.selectUserById(id);
		if(user == null) {
			throw new CustomException(ErrorCode.USER_NOT_FOUND);
		}
		if(user.getPassword().equals(password)) {
			return user;
		}
		throw new CustomException(ErrorCode.WRONG_PASSWORD);
	}

	@Override
	public int modifyUser(User user) throws CustomException{
		User origin = userDao.selectUserByIdx(user.getIdx());
		User tmpUser = userDao.selectUserByEmail(user.getEmail());
		if(tmpUser != null && tmpUser.getIdx() != origin.getIdx()) {
			throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
		}
		tmpUser = userDao.selectUserByNickname(user.getNickname());
		if(tmpUser != null && tmpUser.getIdx() != origin.getIdx()) {
			throw new CustomException(ErrorCode.DUPLICATED_NICKNAME);
		}
		return userDao.updateUser(user);
	}

	@Override
	public int removeUser(int idx) {
		return userDao.deleteUser(idx);
	}

	@Override
	public boolean isIdExist(String id) {
		User user = userDao.selectUserById(id);
		if(user == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isEmailExist(String email) {
		User user = userDao.selectUserByEmail(email);
		if(user == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isNicknameExist(String nickname) {
		User user = userDao.selectUserByNickname(nickname);
		if(user == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean confirmUser(User user) {
		User tmpUser = userDao.selectUserForReset(user);
		if(tmpUser == null) {
			return false;
		}
		return true;
	}

}
