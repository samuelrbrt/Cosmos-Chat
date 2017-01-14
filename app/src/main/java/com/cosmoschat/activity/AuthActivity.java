package com.cosmoschat.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.cosmoschat.R;
import com.cosmoschat.fragment.AuthFragment;
import com.cosmoschat.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthActivity extends AppCompatActivity
    implements FirebaseAuth.AuthStateListener, AuthFragment.OnAuthListener {
	private static final String TAG = "AuthActivity";

	private FirebaseAuth mAuth;
	private DatabaseReference mContactsRef;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);

		mAuth = FirebaseAuth.getInstance();
		mContactsRef = FirebaseDatabase.getInstance().getReference().child("users");
	}

	@Override
	protected void onStart() {
		super.onStart();
		mAuth.addAuthStateListener(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		mAuth.removeAuthStateListener(this);
	}

	@Override
	public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
		FirebaseUser user = firebaseAuth.getCurrentUser();
		if (user != null) {
			Log.e(TAG, "onAuthStateChanged: signed_in: " + user.getDisplayName());
			setResult(RESULT_OK);
			finish();
		}
	}

	@Override
	public void onAuth(String email, String password, boolean isNewUser) {
		if (isNewUser) {
			createUserWithEmailAndPassword(email, password);
		} else {
			signInUserWithEmailAndPassword(email, password);
		}
	}

	private void createUserWithEmailAndPassword(String email, String password) {
		mAuth.createUserWithEmailAndPassword(email, password)
		    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
			    @Override
			    public void onComplete(@NonNull Task<AuthResult> task) {
				    if (task.isSuccessful()) {
					    // Add user information into the contacts node
					    FirebaseUser user = task.getResult().getUser();
					    UserModel model = new UserModel(user.getDisplayName(), user.getPhotoUrl(), user.getUid());
					    mContactsRef.push().setValue(model, user);
				    } else {
					    Toast.makeText(AuthActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
				    }
			    }
		    });
	}

	private void signInUserWithEmailAndPassword(String email, String password) {
		mAuth.signInWithEmailAndPassword(email, password)
		    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
			    @Override
			    public void onComplete(@NonNull Task<AuthResult> task) {
				    Log.e(TAG, "signInUserWithEmailAndPassword: onComplete: " + task.isSuccessful());

				    if (!task.isSuccessful()) {
					    Log.e(TAG, "signInWithEmail", task.getException());
					    Toast.makeText(AuthActivity.this, "Authentication failed.",
						Toast.LENGTH_SHORT).show();
				    }
			    }
		    });
	}
}
