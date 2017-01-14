package com.cosmoschat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class HomePagerAdapter extends FragmentStatePagerAdapter {
	private static final String[] mTitles = new String[]{"CHATS", "CONTACTS"};

	public HomePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return null;
	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mTitles[position];
	}
}
