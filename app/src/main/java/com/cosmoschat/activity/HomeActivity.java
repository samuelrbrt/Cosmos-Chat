package com.cosmoschat.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.cosmoschat.R;
import com.cosmoschat.adapter.HomePagerAdapter;

public class HomeActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		ViewPager mHomeVP = (ViewPager) findViewById(R.id.vp_home);
		mHomeVP.setAdapter(new HomePagerAdapter(getSupportFragmentManager()));
	}
}
