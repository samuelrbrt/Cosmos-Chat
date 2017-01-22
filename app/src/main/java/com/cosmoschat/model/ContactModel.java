package com.cosmoschat.model;

public class ContactModel extends UserModel {
	private String status;

	public ContactModel() {

	}

	public ContactModel(String uId, String name, String avatarURL, String status) {
		super(uId, name, avatarURL);

		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
