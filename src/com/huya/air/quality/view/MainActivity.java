package com.huya.air.quality.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.huya.air.quality.R;

public class MainActivity extends ActionBarActivity {

	private SpinnerAdapter adpter;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActionBar bar = getSupportActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

	Bundle bundle = getIntent().getExtras();
	final String []  citys = bundle.getStringArray("cityarray");
 		adpter = new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_spinner_dropdown_item,
				citys);
 		
 		final String [] url = bundle.getStringArray("urlarray");

		bar.setListNavigationCallbacks(adpter, new OnNavigationListener() {

			@Override
			public boolean onNavigationItemSelected(final int itemPosition,
					final long itemId) {

				// TODO Auto-generated method stub
				Fragment fragmentInfo = new CityAirQualityInfoFragment();

				FragmentTransaction transaction = getSupportFragmentManager()
						.beginTransaction();
				// 将Activity中的内容替换成对应选择的Fragment
				transaction.replace(R.id.air_info, fragmentInfo, url[itemPosition]);
				transaction.commit();

				return true;
			}
		});

	}
	
}
