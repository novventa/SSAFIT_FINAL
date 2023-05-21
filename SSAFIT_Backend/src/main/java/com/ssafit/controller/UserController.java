package com.ssafit.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafit.exception.DuplicatedException;
import com.ssafit.exception.JWTTokenException;
import com.ssafit.model.dto.User;
import com.ssafit.model.service.UserService;
import com.ssafit.util.JWTUtil;

@RestController
@RequestMapping("/api-user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	JWTUtil jwtUtil;

	@Value("${part.upload.path}")
	private String uploadPath;

	@PostMapping("add")
	public ResponseEntity<?> userAdd(User user, MultipartFile file) throws DuplicatedException {

		if (!file.isEmpty() && file.getContentType().startsWith("image")) {
			String originalFileName = file.getOriginalFilename();

			UUID uuid = UUID.randomUUID();

			String fileName = originalFileName.substring(originalFileName.lastIndexOf("//") + 1);
			String folderPath = user.getId();

			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

			File uploadPathFolder = new File(uploadPath, folderPath);

			if (!uploadPathFolder.exists())
				uploadPathFolder.mkdirs();

			Path savePath = Paths.get(saveName);

			try {
				file.transferTo(savePath);
				user.setImage(uuid + "_" + fileName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		int result = userService.addUser(user);
		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}

	@DeleteMapping("remove/{idx}")
	public ResponseEntity<?> userRemove(@PathVariable int idx) {
		int result = userService.removeUser(idx);
		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@GetMapping("list")
	public ResponseEntity<?> userList() {
		List<User> list = userService.findAllUsers();
		if (list == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@GetMapping("searchId")
	public ResponseEntity<?> idFind(User user) {
		String id = userService.findId(user);
		if (id == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(id, HttpStatus.OK);
	}

	@GetMapping("check/id/{id}")
	public ResponseEntity<Void> idCheck(@PathVariable String id) throws DuplicatedException {
		if (userService.isIdExist(id)) {
			throw new DuplicatedException("이미 사용중인 id입니다.");
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("check/email/{email}")
	public ResponseEntity<Void> emailCheck(@PathVariable String email) throws DuplicatedException {
		if (userService.isEmailExist(email)) {
			throw new DuplicatedException("이미 사용중인 이메일입니다.");
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("check/nickname/{nickname}")
	public ResponseEntity<Void> nicknameCheck(@PathVariable String nickname) throws DuplicatedException {
		if (userService.isNicknameExist(nickname)) {
			throw new DuplicatedException("이미 사용중인 닉네임입니다.");
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(String id, String password) {
		HttpStatus status = null;
		HashMap<String, Object> result = new HashMap<>();
		User user = userService.login(id, password);
		if (user != null) {
			try {
				result.put("access-token", jwtUtil.createToken("userIdx", String.valueOf(user.getIdx())));
			} catch (Exception e) {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			status = HttpStatus.ACCEPTED;
		} else {
			status = HttpStatus.NO_CONTENT;
		}
		status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

//	@GetMapping()
//	public ResponseEntity<Void> logout(HttpSession session) {
//
//		session.removeAttribute("access-token");
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}

	@GetMapping("details/{idx}")
	public ResponseEntity<?> userDetails(@PathVariable int idx) {
		User user = userService.findUser(idx);
		if (user == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PutMapping("modify")
	public ResponseEntity<?> userModify(User user) throws DuplicatedException {
		int result = userService.modifyUser(user);
		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
