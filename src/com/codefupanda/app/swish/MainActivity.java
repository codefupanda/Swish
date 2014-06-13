/*
 * Copyright (C) Shashank Kulkarni - Shashank.physics AT gmail DOT com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.codefupanda.app.swish;

import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.codefupanda.app.swish.adapter.CustomAdapter;
import com.codefupanda.app.swish.constant.Constants;
import com.codefupanda.app.swish.dao.BirthdayContactDao;
import com.codefupanda.app.swish.dao.Dao;
import com.codefupanda.app.swish.entity.Contact;
import com.codefupanda.app.swish.util.SMSUtil;

/**
 * The main activity. 
 * Launcher activity, the first screen displayed.
 *  
 * @author Shashank
 */
public class MainActivity extends ActionBarActivity implements
		OnItemClickListener {

	/** splash screen displayed on app opening. */
	private static final int WELCOME_SCREEN_LENGTH = 2000;
	
	/** Holds the view for displaying list of contacts. */
	private ListView mylistview;
	
	/** For saving and retrieving message text. */
	private Dao dao;
	
	/** Get people with birthday today. */
	private BirthdayContactDao birthdayContactDao;
	
	/** contacts cache */
	private List<Contact> contacts;

	/** The adapter. */
	private CustomAdapter customAdapter;
	
	/**
	 * Called when activity created. Some init values.
	 * 
	 * Checks if application is run for first time,
	 * if so starts {@link WelcomeActivity}  
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences prefs = getSharedPreferences("com.codefupanda.app.swish", MODE_PRIVATE);
		
		if (prefs.getBoolean("firstrun", true)) {
			Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
			startActivity(intent);
			prefs.edit().putBoolean("firstrun", false).commit();
            finish();
        }
		
		setContentView(R.layout.activity_main);
		
		dao = new Dao();
		birthdayContactDao = new BirthdayContactDao(getApplicationContext());
		contacts = birthdayContactDao.getTodaysBirthdayContacts();
		
		mylistview = (ListView) findViewById(R.id.list);
		
		customAdapter = new CustomAdapter(MainActivity.this, contacts);
		mylistview.setAdapter(customAdapter);
		mylistview.setOnItemClickListener(MainActivity.this);
		
		Button swishButton = (Button) findViewById(R.id.swishButton);
		swishButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				SMSUtil.sendSms(getApplicationContext(), customAdapter.getCheckedContacts(), dao.getSmsText(getApplicationContext()));
			}
		});
	}
	
	/**
	 * Displays ListView after a delay.
	 * 
	 * Flash is disabled when main activity is called after the welcome screen.
	 */
	@Override
	protected void onResume() {
		Bundle extras = getIntent().getExtras();
		
		if(extras != null && !extras.getBoolean(Constants.SHOW_FLASH)) {
			showHomeScreen();
		} else {
			new Handler().postDelayed(new Runnable() {
				public void run() {
					showHomeScreen();
				}
			}, WELCOME_SCREEN_LENGTH);
		}
		
		super.onResume();
	}

	/**
	 * Makes list of contacts visible and logo invisible.
	 */
	private void showHomeScreen() {
		findViewById(R.id.logo).setVisibility(View.INVISIBLE);
		if(contacts.size() > 0) {
			findViewById(R.id.swishButton).setVisibility(View.VISIBLE);
			mylistview.setVisibility(View.VISIBLE);
		} else {
			findViewById(R.id.noBdayToday).setVisibility(View.VISIBLE);
		}
	}
	
	/**
	 * DO NOTHING!?
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	}

	/**
	 * Inflate menu.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	/**
	 * On menu clicked.
	 * 
	 * If settings clicked, shows the setting screen.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	    // open settings activity
	        case R.id.action_settings:
	        	startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
	            return true;
	        case R.id.action_help:
	        	startActivity(new Intent(getApplicationContext(), HelpActivity.class));
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}