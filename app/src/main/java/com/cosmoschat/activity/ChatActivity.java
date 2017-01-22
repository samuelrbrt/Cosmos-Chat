package com.cosmoschat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cosmoschat.R;
import com.cosmoschat.adapter.ChatAdapter;

@SuppressWarnings("ConstantConditions")
public class ChatActivity extends AppCompatActivity {
	private ChatAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		mAdapter = new ChatAdapter();

		RecyclerView chatRV = (RecyclerView) findViewById(R.id.rv_chat);
		chatRV.setLayoutManager(new LinearLayoutManager(this));
		chatRV.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
		chatRV.setAdapter(mAdapter);
	}
}
