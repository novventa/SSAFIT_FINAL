package com.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.exception.DuplicatedException;
import com.ssafit.model.dto.User;
import com.ssafit.model.service.UserService;

@RestController
@RequestMapping("/api-user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping()
	public ResponseEntity<?> userAdd(User user) throws DuplicatedException {
		int result = userService.addUser(user);
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
	@DeleteMapping("{idx}")
	public ResponseEntity<?> userRemove(@PathVariable int idx) {
		int result = userService.removeUser(idx);
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<?> userList() {
		List<User> list = userService.findAllUsers();
		if(list == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
//	@PostMapping()
//	public ResponseEntity<?> login(String id, String password) {
//		User user = userService.login(id, password);
//		if(user == null) {
//			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
//		}
//		return new ResponseEntity<User>(user, HttpStatus.OK);
//	}
//	
//	@GetMapping()
//	public ResponseEntity<?> logout() {
//		
//	}
	
	@GetMapping("{idx}")
	public ResponseEntity<?> userDetails(@PathVariable int idx) {
		User user = userService.findUser(idx);
		if(user == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<?> userModify(User user) throws DuplicatedException {
		int result = userService.modifyUser(user);
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
