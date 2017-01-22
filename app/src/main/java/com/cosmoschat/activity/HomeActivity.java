package com.cosmoschat.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cosmoschat.R;
import com.cosmoschat.adapter.HomePagerAdapter;
import com.cosmoschat.fragment.ContactsFragment;
import com.cosmoschat.model.ContactModel;
import com.cosmoschat.view.SlidingTabLayout;

@SuppressWarnings("ConstantConditions")
public class HomeActivity extends AppCompatActivity implements ContactsFragment.OnContactListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

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

		ViewPager homeVP = (ViewPager) findViewById(R.id.vp_home);
		homeVP.setAdapter(new HomePagerAdapter(getSupportFragmentManager()));

		SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.st_home);
		slidingTabLayout.setCustomTabView(R.layout.sliding_tab, R.id.tv_title);
		slidingTabLayout.setViewPager(homeVP);
		slidingTabLayout.setSelectedIndicatorColors(Color.WHITE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onContact(ContactModel model) {
		Intent intent = new Intent(this, ChatActivity.class);
		intent.putExtra(Intent.EXTRA_UID, model.getUId());
		startActivity(intent);
	}
}
