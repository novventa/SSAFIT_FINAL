package com.ssafit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafit.model.dto.User;

@Repository
public interface UserDao {
	List<User> selectAll();

	String selectId(User user);
	
	User selectUserByIdx(int idx);

	User selectUserById(String id);

	User selectUserByEmail(String email);

	User selectUserByNickname(String nickname);
	
	List<User> selectUsersByNickname(String nickname);
	
	User selectUserForReset(User user);

	int insertUser(User user);

	int deleteUser(int idx);
	
	int updateUser(User user);
	
	int updatePassword(User user);

}
