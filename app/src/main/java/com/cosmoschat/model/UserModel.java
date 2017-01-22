package com.cosmoschat.model;

import java.io.Serializable;

public class UserModel implements Serializable {
	private String uId, name, avatar;

	public UserModel() {
	}

	public UserModel(String uId, String name, String avatar) {
		this.uId = uId;
		this.name = name;
		this.avatar = avatar;
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

	public String getUId() {
		return uId;
	}

	public void setUId(String uId) {
		this.uId = uId;
	}
}
