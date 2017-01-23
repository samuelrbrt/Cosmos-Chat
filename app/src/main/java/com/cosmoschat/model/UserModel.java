package com.cosmoschat.model;

import java.io.Serializable;

public class UserModel implements Serializable {
	private String uid, name, avatar;

	public UserModel() {
	}

	public UserModel(String uid, String name, String avatar) {
		this.uid = uid;
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}
