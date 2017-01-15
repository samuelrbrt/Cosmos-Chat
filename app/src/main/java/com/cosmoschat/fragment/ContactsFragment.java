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
import com.cosmoschat.adapter.ContactsListAdapter;
import com.cosmoschat.database.FirebaseRef;
import com.cosmoschat.model.UserModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactsFragment extends Fragment implements ContactsListAdapter.OnContactListItemClickLister, ChildEventListener {
	private static final String TAG = "ContactsFragment";
	private ContactsListAdapter mAdapter;
	private DatabaseReference childRef;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		childRef = FirebaseDatabase.getInstance().getReference().child(FirebaseRef.USERS);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		RecyclerView contactsRV = (RecyclerView) inflater.inflate(R.layout.fragment_list, container, false);
		contactsRV.setLayoutManager(new LinearLayoutManager(getContext()));
		mAdapter = new ContactsListAdapter(this);
		contactsRV.setAdapter(mAdapter);
		return contactsRV;
	}

	@Override
	public void onStart() {
		super.onStart();

		childRef.addChildEventListener(this);
	}

	@Override
	public void onChildAdded(DataSnapshot dataSnapshot, String s) {
		Log.e(TAG, "onDataChange: New snapshot");
		UserModel model = dataSnapshot.getValue(UserModel.class);
		mAdapter.addNewContact(model);
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

	}

	@Override
	public void onAvatar(int position) {

	}

	@Override
	public void onItem(int position) {

	}
}
