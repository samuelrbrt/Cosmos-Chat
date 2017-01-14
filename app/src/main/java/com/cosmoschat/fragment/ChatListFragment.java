package com.cosmoschat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cosmoschat.R;
import com.cosmoschat.adapter.ChatListAdapter;
import com.cosmoschat.model.BaseChatModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatListFragment extends Fragment implements ValueEventListener, ChatListAdapter.OnChatListItemClickLister {
	private ChatListAdapter mAdapter;
	private DatabaseReference childRef;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		childRef = FirebaseDatabase.getInstance().getReference().child("chat_list");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		RecyclerView mChatListRV = (RecyclerView) inflater.inflate(R.layout.fragment_list, container, false);
		mChatListRV.setLayoutManager(new LinearLayoutManager(getContext()));
		mAdapter = new ChatListAdapter(this);
		mChatListRV.setAdapter(mAdapter);
		return mChatListRV;
	}

	@Override
	public void onStart() {
		super.onStart();

		childRef.addValueEventListener(this);
	}

	@Override
	public void onDataChange(DataSnapshot dataSnapshot) {
		BaseChatModel model = dataSnapshot.getValue(BaseChatModel.class);

		if (model != null) {
			mAdapter.addNewModel(model);
		}
	}

	@Override
	public void onCancelled(DatabaseError databaseError) {

	}

	@Override
	public void onAvatar(int position) {

	}

	@Override
	public void onItem(int position) {

	}
}
