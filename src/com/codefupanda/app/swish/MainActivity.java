package com.codefupanda.app.swish;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.codefupanda.app.swish.adapter.CustomAdapter;
import com.codefupanda.app.swish.dao.BirthdayContactDao;
import com.codefupanda.app.swish.entity.Contact;
import com.codefupanda.app.swish.util.AlarmUtil;

public class MainActivity extends ActionBarActivity implements
		OnItemClickListener {

	private List<Contact> contacts;
	private ListView mylistview;
	private CustomAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AlarmUtil.setOnetimeTimer(this);
		
		contacts = getContacts();

		mylistview = (ListView) findViewById(R.id.list);

		adapter = new CustomAdapter(this, contacts);
		mylistview.setAdapter(adapter);

		mylistview.setOnItemClickListener(this);

	} // oncreate end

	private List<Contact> getContacts() {
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(new Contact("Shashank", R.drawable.blank_profile_pic,
				new Date()));
		contacts.add(new Contact("Shankar", R.drawable.blank_profile_pic,
				new Date()));
		contacts.add(new Contact("Santosh", R.drawable.blank_profile_pic,
				new Date()));
		return new BirthdayContactDao(this).getTodaysBirthdayContacts();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_search:
	            return true;
	        case R.id.action_settings:
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}