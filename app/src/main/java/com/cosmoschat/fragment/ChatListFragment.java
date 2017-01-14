package com.cosmoschat.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cosmoschat.R;
import com.cosmoschat.adapter.ChatListAdapter;

public class ChatListFragment extends Fragment {
	private RecyclerView mChatListRV;
	private ChatListAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		mChatListRV = (RecyclerView) inflater.inflate(R.layout.fragment_chat_list, container, false);
		mChatListRV.setLayoutManager(new LinearLayoutManager(getContext()));
		mAdapter = new ChatListAdapter();
		mChatListRV.setAdapter(mAdapter);
		return mChatListRV;
	}
}
