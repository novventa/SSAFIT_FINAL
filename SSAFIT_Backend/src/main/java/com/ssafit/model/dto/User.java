package com.ssafit.model.dto;

public class User {
	private int idx;
	private String id;
	private String password;
	private String nickname;
	private String email;
	private String image = "none";
	
	public User() {
	}

	
	public User(int idx, String id, String password, String nickname, String email, String image) {
		this.idx = idx;
		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.image = image;
	}


	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
