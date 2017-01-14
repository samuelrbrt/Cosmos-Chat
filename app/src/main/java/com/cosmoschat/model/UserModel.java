package com.cosmoschat.model;


import android.net.Uri;

import java.io.Serializable;

public class UserModel implements Serializable {
	private final Uri avatarURL;
	private final String name, userId;

	public UserModel(String name, Uri avatarURL, String userId) {
		this.name = name;
		this.avatarURL = avatarURL;
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public Uri getAvatarURL() {
		return avatarURL;
	}

	public String getUserId() {
		return userId;
	}
}
