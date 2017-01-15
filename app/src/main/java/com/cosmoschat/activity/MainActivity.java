package com.cosmoschat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.cosmoschat.R;
import com.cosmoschat.database.FirebaseRef;
import com.cosmoschat.model.UserModel;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@SuppressWarnings("ConstantConditions")
public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";
	private static final int RC_AUTH = 0;

	private FirebaseAuth mAuth;
	private DatabaseReference mUsersRef;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mAuth = FirebaseAuth.getInstance();
		mUsersRef = FirebaseDatabase.getInstance().getReference().child(FirebaseRef.USERS);

		if (mAuth.getCurrentUser() == null) {
			Log.e(TAG, "onCreate: Authenticating user");

			Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
			    .setProviders(
				AuthUI.EMAIL_PROVIDER,
				AuthUI.GOOGLE_PROVIDER,
				AuthUI.FACEBOOK_PROVIDER
			    )
			    .build();

			startActivityForResult(intent, RC_AUTH);
		} else {
			Log.e(TAG, "onCreate: User already authenticated");
			startActivity(new Intent(this, HomeActivity.class));
			finish();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == RC_AUTH && resultCode == RESULT_OK) {
			FirebaseUser user = mAuth.getCurrentUser();
			UserModel model = new UserModel(user.getDisplayName(), user.getPhotoUrl().toString(), getString(R.string
			    .default_status));

			mUsersRef.child(user.getUid()).setValue(model)
			    .addOnCompleteListener(new OnCompleteListener<Void>() {
				    @Override
				    public void onComplete(@NonNull Task<Void> task) {
					    Log.e(TAG, "onComplete: is user inserted successfully: " + task.isSuccessful());

					    startActivity(new Intent(MainActivity.this, HomeActivity.class));
					    finish();
				    }
			    });
		} else {
			Log.e(TAG, "onActivityResult: not authenticated");
			finish();
		}
	}
}
