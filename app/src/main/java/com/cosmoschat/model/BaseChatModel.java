package com.cosmoschat.model;


import android.net.Uri;

public class BaseChatModel extends UserModel {
	private final String time, recent;

	public BaseChatModel(Uri avatarURL, String name, String time, String recent, String userId) {
		super(name, avatarURL, userId);
		this.time = time;
		this.recent = recent;
	}

	public String getTime() {
		return time;
	}

	public String getRecent() {
		return recent;
	}
}
