package com.cosmoschat.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.cosmoschat.R;

public class AuthFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
	private Button mOkBtn;
	private EditText mEmailET, mPasswordET;
	private TextInputLayout mEmailTIL, mPasswordTIL;
	private SwitchCompat mUserTypeSC;

	private OnAuthListener mListener;

	private String mEmail, mPassword;
	private boolean isNewUser = false;

	public AuthFragment() {
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnAuthListener) {
			mListener = (OnAuthListener) context;
		} else {
			throw new RuntimeException(context.toString() + " must implement OnAuthListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(R.layout.fragment_auth, container, false);

		mUserTypeSC = (SwitchCompat) fragmentView.findViewById(R.id.sc_user_type);
		mEmailTIL = (TextInputLayout) fragmentView.findViewById(R.id.til_email);
		mEmailET = (EditText) fragmentView.findViewById(R.id.et_email);
		mPasswordTIL = (TextInputLayout) fragmentView.findViewById(R.id.til_password);
		mPasswordET = (EditText) fragmentView.findViewById(R.id.et_password);
		mOkBtn = (Button) fragmentView.findViewById(R.id.btn_ok);

		return fragmentView;
	}

	@Override
	public void onStart() {
		super.onStart();
		mOkBtn.setOnClickListener(this);
		mUserTypeSC.setOnCheckedChangeListener(this);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	@Override
	public void onClick(View v) {
		if (isInputValid()) {
			mListener.onAuth(mEmail, mPassword, isNewUser);
		}
	}

	private boolean isInputValid() {
		mEmail = mEmailET.getText().toString();
		mPassword = mPasswordET.getText().toString();
		boolean isValid = true;

		if (!mEmail.matches("[a-zA-Z][a-zA-Z 0-9.-]*@gmail.com")) {
			isValid = false;
			mEmailTIL.setError(getString(R.string.invalid_email));
		} else {
			mEmailTIL.setError(null);
		}

		if (mPassword.length() < 8) {
			isValid = false;
			mPasswordTIL.setError(getString(R.string.invalid_password));
		} else {
			mPasswordTIL.setError(null);
		}

		return isValid;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		isNewUser = isChecked;

		if (isChecked) {
			mOkBtn.setText(R.string.sign_up);
		} else {
			mOkBtn.setText(R.string.log_in);
		}
	}

	public interface OnAuthListener {
		void onAuth(String email, String password, boolean isNewUser);
	}
}
