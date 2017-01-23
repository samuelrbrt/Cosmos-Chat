package com.cosmoschat.model;


public class GroupModel {
	private static final String SEPARATOR = "_";
	private String uids;

	public GroupModel() {

	}

	public GroupModel(String uid, String uid1) {
		uids = uid + SEPARATOR + uid1;
	}

	public String getUids() {
		return uids;
	}
}
