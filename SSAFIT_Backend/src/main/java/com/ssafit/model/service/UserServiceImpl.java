package com.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.exception.DuplicatedException;
import com.ssafit.model.dao.UserDao;
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
	public User findUser(int idx) {
		return userDao.selectUserByIdx(idx);
	}
	
	@Override
	public int addUser(User user) throws DuplicatedException {
		if(isIdExist(user.getId())) {
			throw new DuplicatedException("이미 사용중인 아이디입니다.");
		}
		if(isEmailExist(user.getEmail())) {
			throw new DuplicatedException("이미 사용중인 이메일입니다.");
		}
		if(isNicknameExist(user.getNickname())) {
			throw new DuplicatedException("이미 사용중인 닉네임입니다.");
		}
		return userDao.insertUser(user);
	}

	@Override
	public User login(String id, String password) {
		User user = userDao.selectUserById(id);
		if(user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

	@Override
	public int modifyUser(User user) throws DuplicatedException {
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

}
