package com.cosmoschat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cosmoschat.R;
import com.cosmoschat.adapter.UserListAdapter;
import com.cosmoschat.database.FirebaseRef;
import com.cosmoschat.model.UserModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsersFragment extends Fragment implements UserListAdapter.OnContactListItemClickLister, ChildEventListener {
	private static final String TAG = "UsersFragment";
	private UserListAdapter mAdapter;
	private DatabaseReference usersRef;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		usersRef = FirebaseDatabase.getInstance().getReference().child(FirebaseRef.USERS);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		RecyclerView contactsRV = (RecyclerView) inflater.inflate(R.layout.fragment_list, container, false);
		contactsRV.setLayoutManager(new LinearLayoutManager(getContext()));
		mAdapter = new UserListAdapter(this);
		contactsRV.setAdapter(mAdapter);

		usersRef.addChildEventListener(this);

		return contactsRV;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		usersRef.removeEventListener(this);
	}

	@Override
	public void onChildAdded(DataSnapshot dataSnapshot, String s) {
		Log.e(TAG, "onDataChange: New snapshot");
		UserModel model = dataSnapshot.getValue(UserModel.class);
		mAdapter.addNewUser(model);
	}

	@Override
	public void onChildChanged(DataSnapshot dataSnapshot, String s) {

	}

	@Override
	public void onChildRemoved(DataSnapshot dataSnapshot) {

	}

	@Override
	public void onChildMoved(DataSnapshot dataSnapshot, String s) {

	}

	@Override
	public void onCancelled(DatabaseError databaseError) {
		Log.e(TAG, databaseError.getMessage());
	}

	@Override
	public void onAvatar(int position) {

	}

	@Override
	public void onItem(int position) {

	}
}
