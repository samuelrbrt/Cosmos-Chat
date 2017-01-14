package com.cosmoschat.model;

import android.net.Uri;

public class ContactModel extends UserModel {

	private final String status;

	public ContactModel(Uri avatarURL, String name, String status, String userId) {
		super(name, avatarURL, userId);

		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
