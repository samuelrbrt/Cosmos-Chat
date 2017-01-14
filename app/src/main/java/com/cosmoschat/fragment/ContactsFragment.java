package com.cosmoschat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cosmoschat.R;
import com.cosmoschat.adapter.ContactsListAdapter;

public class ContactsFragment extends Fragment {
	private ContactsListAdapter mAdapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		RecyclerView contactsRV = (RecyclerView) inflater.inflate(R.layout.fragment_list, container, false);

		return contactsRV;
	}
}
