package com.cosmoschat.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cosmoschat.R;
import com.cosmoschat.adapter.ContactsAdapter;
import com.cosmoschat.database.FirebaseRef;
import com.cosmoschat.model.ContactModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactsFragment extends Fragment implements ContactsAdapter.OnContactsItemClickLister, ChildEventListener {
	private static final String TAG = "ContactsFragment";

	private ContactsAdapter mAdapter;
	private DatabaseReference mUsersRef;
	private OnContactListener mListener;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);

		try {
			mListener = (OnContactListener) context;
		} catch (ClassCastException e) {
			throw new IllegalStateException(getActivity().getLocalClassName() + " must implement " +
			    "OnContactListener interface.");
		}
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mUsersRef = FirebaseDatabase.getInstance().getReference().child(FirebaseRef.USERS);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		RecyclerView usersRV = (RecyclerView) inflater.inflate(R.layout.fragment_list, container, false);

		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		usersRV.setLayoutManager(layoutManager);

		DividerItemDecoration decoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
		usersRV.addItemDecoration(decoration);

		mAdapter = new ContactsAdapter(this);
		usersRV.setAdapter(mAdapter);

		mUsersRef.addChildEventListener(this);

		return usersRV;
	}

	@Override
	public void onDetach() {
		super.onDetach();

		mListener = null;
		mUsersRef.removeEventListener(this);
	}

	@Override
	public void onChildAdded(DataSnapshot dataSnapshot, String s) {
		ContactModel model = dataSnapshot.getValue(ContactModel.class);
		model.setUid(dataSnapshot.getKey());
		mAdapter.addNewUser(model);

		Log.d(TAG, "New USER: userId: " + dataSnapshot.getKey() + ", Name: " + model.getName());
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
		Log.e(TAG, databaseError.getMessage(), databaseError.toException());
	}

	@Override
	public void onAvatar(ContactModel model, int position) {

	}

	@Override
	public void onItem(ContactModel model, int position) {
		mListener.onContact(model);
	}

	public interface OnContactListener {
		void onContact(ContactModel model);
	}
}
