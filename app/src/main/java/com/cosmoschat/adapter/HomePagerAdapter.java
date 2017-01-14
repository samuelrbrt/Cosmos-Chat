package com.cosmoschat.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cosmoschat.fragment.ChatListFragment;
import com.cosmoschat.fragment.ContactsFragment;

public class HomePagerAdapter extends FragmentStatePagerAdapter {
	private static final String[] mTitles = new String[]{"CHATS", "CONTACTS"};

	public HomePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return position == 0 ? new ChatListFragment() : new ContactsFragment();
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
