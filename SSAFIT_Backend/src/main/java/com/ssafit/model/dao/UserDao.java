package com.ssafit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafit.model.dto.User;

@Repository
public interface UserDao {
	List<User> selectAll();

	User selectUserById(String id);

	User selectUserByEmail(String email);

	User selectUserByNickname(String nickname);

	int insertUser(User user);

	int deleteUser(String id);

	int updateUser(User user);

}
