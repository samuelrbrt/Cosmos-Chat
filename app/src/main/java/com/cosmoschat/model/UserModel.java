package com.cosmoschat.model;

public class UserModel {
	private String avatar;
	private String name, status;

	public UserModel() {

	}

	public UserModel(String name, String avatar, String status) {
		this.name = name;
		this.avatar = avatar;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
