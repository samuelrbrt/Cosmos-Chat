package com.cosmoschat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.cosmoschat.R;
import com.cosmoschat.adapter.ChatAdapter;
import com.cosmoschat.database.FirebaseRef;
import com.cosmoschat.model.ChatModel;
import com.cosmoschat.model.ContactModel;
import com.cosmoschat.model.GroupModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

@SuppressWarnings("ConstantConditions")
public class ChatActivity extends AppCompatActivity implements ChildEventListener {
	private static final String TAG = "ChatActivity";

	private ChatAdapter mAdapter;
	private DatabaseReference mGroupRef;
	private String mGroupId;
	private ContactModel mToUser;
	private FirebaseUser mUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);

		mToUser = (ContactModel) getIntent().getSerializableExtra(Intent.EXTRA_UID);
		mUser = FirebaseAuth.getInstance().getCurrentUser();
		mGroupRef = FirebaseDatabase.getInstance().getReference().child(FirebaseRef.GROUP);
		mGroupRef.orderByChild(FirebaseRef.GROUP_GID_UIDS).equalTo(mUser.getUid() + "_" + mToUser.getUid())
		    .addListenerForSingleValueEvent(new ValueEventListener() {
			    @Override
			    public void onDataChange(DataSnapshot dataSnapshot) {
				    if (dataSnapshot.hasChildren()) {
					    mGroupId = dataSnapshot.getChildren().iterator().next().getKey();
				    } else {
					    mGroupId = mGroupRef.push().getKey();
					    mGroupRef.child(mGroupId).setValue(new GroupModel(mUser.getUid(), mToUser.getUid()));
				    }

				    Log.d(TAG, "GroupId :" + mGroupId);
			    }

			    @Override
			    public void onCancelled(DatabaseError databaseError) {
				    Log.e(TAG, databaseError.getMessage());
			    }
		    });

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setTitle(mToUser.getName());
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		mAdapter = new ChatAdapter();
		RecyclerView chatRV = (RecyclerView) findViewById(R.id.rv_chat);
		chatRV.setLayoutManager(new LinearLayoutManager(this));
		chatRV.setAdapter(mAdapter);
	}

	@Override
	public void onChildAdded(DataSnapshot dataSnapshot, String s) {
		mAdapter.addItem(dataSnapshot.getValue(ChatModel.class));
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
}
