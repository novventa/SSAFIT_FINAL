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

import com.ssafit.exception.CustomException;
import com.ssafit.exception.JWTTokenException;
import com.ssafit.model.dto.ErrorCode;
import com.ssafit.model.dto.User;
import com.ssafit.model.service.UserService;
import com.ssafit.util.JWTUtil;

@RestController
@RequestMapping("/api-user")
public class UserController {

	private static final String SUCCESS = "success";
	
	@Autowired
	UserService userService;

	@Autowired
	JWTUtil jwtUtil;

	@Value("${part.upload.path}")
	private String uploadPath;

	@PostMapping("add")
	public ResponseEntity<?> userAdd(User user, MultipartFile file) throws CustomException{

		if (file != null && !file.isEmpty() && file.getContentType().startsWith("image")) {
			String originalFileName = file.getOriginalFilename();

			UUID uuid = UUID.randomUUID();

			String fileName = originalFileName.substring(originalFileName.lastIndexOf("//") + 1);
			String folderPath = user.getId();

			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

			File uploadPathFolder = new File(uploadPath, folderPath);

			if (!uploadPathFolder.exists())
				uploadPathFolder.mkdirs();

			Path savePath = Paths.get(saveName);
			user.setImage(uuid + "_" + fileName);
			userService.addUser(user);
			try {
				file.transferTo(savePath);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		else {
			userService.addUser(user);			
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}

	@DeleteMapping("remove/{idx}")
	public ResponseEntity<?> userRemove(@PathVariable int idx) throws CustomException {
		int result = userService.removeUser(idx);
		if (result == 0) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@GetMapping("list")
	public ResponseEntity<?> userList() throws CustomException {
		List<User> list = userService.findAllUsers();
		if (list == null) {
			throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@GetMapping("searchId")
	public ResponseEntity<?> idFind(User user) throws CustomException {
		String id = userService.findId(user);
		if (id == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(id, HttpStatus.OK);
	}

	@GetMapping("check/id/{id}")
	public ResponseEntity<Void> idCheck(@PathVariable String id) throws CustomException {
		if (userService.isIdExist(id)) {
			throw new CustomException(ErrorCode.DUPLICATED_ID);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("check/email/{email}")
	public ResponseEntity<Void> emailCheck(@PathVariable String email) throws CustomException {
		if (userService.isEmailExist(email)) {
			throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("check/nickname/{nickname}")
	public ResponseEntity<Void> nicknameCheck(@PathVariable String nickname) throws CustomException {
		if (userService.isNicknameExist(nickname)) {
			throw new CustomException(ErrorCode.DUPLICATED_NICKNAME);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(String id, String password) throws CustomException {
		HttpStatus status = null;
		HashMap<String, Object> result = new HashMap<>();
		User user = userService.login(id, password);
		try {
			result.put("access-token", jwtUtil.createToken("userIdx", String.valueOf(user.getIdx())));
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

//	@GetMapping()
//	public ResponseEntity<Void> logout(HttpSession session) {
//
//		session.removeAttribute("access-token");
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}

	@GetMapping("details/{idx}")
	public ResponseEntity<?> userDetails(@PathVariable int idx) throws CustomException {
		User user = userService.findUser(idx);
		if (user == null) {
			throw new CustomException(ErrorCode.USER_NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PutMapping("modify")
	public ResponseEntity<?> userModify(User user, MultipartFile file) throws CustomException {
		if (file != null && !file.isEmpty() && file.getContentType().startsWith("image")) {
			String originalFileName = file.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			String fileName = originalFileName.substring(originalFileName.lastIndexOf("//") + 1);
			String folderPath = user.getId();
			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
			File uploadPathFolder = new File(uploadPath, folderPath);

			if (!uploadPathFolder.exists()) {
				uploadPathFolder.mkdirs();
			}

			Path savePath = Paths.get(saveName);
			user.setImage(uuid + "_" + fileName);
			userService.modifyUser(user);
			try {
				file.transferTo(savePath);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			userService.modifyUser(user);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
}
