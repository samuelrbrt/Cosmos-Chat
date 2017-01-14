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
import com.cosmoschat.adapter.ContactsListAdapter;
import com.cosmoschat.model.ContactModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContactsFragment extends Fragment implements ValueEventListener, ContactsListAdapter.OnContactListItemClickLister {
	private ContactsListAdapter mAdapter;
	private DatabaseReference childRef;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		childRef = FirebaseDatabase.getInstance().getReference().child("users");
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

		childRef.addValueEventListener(this);
	}

	@Override
	public void onDataChange(DataSnapshot dataSnapshot) {
		ContactModel model = dataSnapshot.getValue(ContactModel.class);

		if (model != null) {
			mAdapter.addNewContact(model);
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
