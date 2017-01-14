package com.cosmoschat.model;


public class BaseChatModel {
	private String avatarURL, name, time, recent;

	public BaseChatModel() {

	}

	public BaseChatModel(String avatarURL, String name, String time, String recent) {
		this.avatarURL = avatarURL;
		this.name = name;
		this.time = time;
		this.recent = recent;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRecent() {
		return recent;
	}

	public void setRecent(String recent) {
		this.recent = recent;
	}
}
