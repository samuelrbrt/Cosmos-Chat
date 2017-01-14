package com.cosmoschat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cosmoschat.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";
	private static final int RC_AUTH = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (FirebaseAuth.getInstance().getCurrentUser() == null) {
			startActivityForResult(new Intent(this, AuthActivity.class), RC_AUTH);
		} else {
			//TODO: launch message list activity
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			////TODO: launch message list activity
		} else {
			finish();
		}
	}
}
