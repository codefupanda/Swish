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
package com.codefupanda.app.swish.adapter;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.codefupanda.app.swish.R;
import com.codefupanda.app.swish.entity.Contact;

/**
 * Adapter to populate list of contacts. 
 *  
 * @author Shashank
 */
public class CustomAdapter extends BaseAdapter {

	private Context context;
	
	/** List of contacts */
	private List<Contact> contacts;

	/** Records unchecked items */
	private boolean[] itemsUnChecked;

	/**
	 * Constructor.
	 * 
	 * @param context
	 * @param contacts
	 */
	public CustomAdapter(Context context, List<Contact> contacts) {
		this.context = context;
		this.contacts = contacts;
		itemsUnChecked = new boolean[contacts.size()];
	}

	/**
	 * Get total contacts.
	 */
	@Override
	public int getCount() {
		return contacts.size();
	}

	/**
	 * Get contact at position.
	 */
	@Override
	public Object getItem(int position) {
		return contacts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return contacts.indexOf(getItem(position));
	}

	/** private view holder class */
	private class ViewHolder {
		ImageView profilePic;
		TextView name;
		TextView phoneNumber;
		CheckBox checkBox;
	}

	/**
	 * Populate the view.
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item, null);
			final ViewHolder holder = new ViewHolder();

			// add or setting data's to listItem row
			Contact contact = contacts.get(position);
			
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.profilePic = (ImageView) convertView
					.findViewById(R.id.profile_pic);
			holder.phoneNumber = (TextView) convertView
					.findViewById(R.id.phone_number);
			holder.checkBox = (CheckBox) convertView.findViewById(R.id.chk);
			
			// In case contact have picture assigned to it
			// if not use the defualt pic
			holder.profilePic.setImageURI(contact.getProfilePicUri());			
			if(holder.profilePic.getDrawable() == null) {
				holder.profilePic.setImageResource(R.drawable.blank_profile_pic);
			}
			
			holder.name.setText(contact.getName());
			holder.phoneNumber.setText(getPhoneNumber(contact));

			holder.checkBox.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (holder.checkBox.isChecked()) {
						itemsUnChecked[position] = false;
					} else {
						itemsUnChecked[position] = true;
					}
				}
			});

			convertView.setTag(holder);
		}

		return convertView;
	}

	/**
	 * A helper method to get contact numbers.
	 *  
	 * @param contact contact
	 * @return phone numbers separated by comma
	 */
	private CharSequence getPhoneNumber(Contact contact) {
		if(contact.getPhoneNumbers() == null || contact.getPhoneNumbers().size() == 0) {
			return null;
		}
		
		StringBuilder builder = new StringBuilder();
		
		for(String phNumber: contact.getPhoneNumbers()) {
			builder.append(phNumber);
			builder.append(", ");
		}
		// remove last ", " and convert to string
		return builder.subSequence(0, builder.length() -2);
	}

	/**
	 * Get all the contacts which are checked.
	 * 
	 * @return list of checked contacts
	 */
	public List<Contact> getCheckedContacts() {
		List<Contact> checkedContacts = new LinkedList<Contact>();
		
		for(int i=0; i<contacts.size(); i++) {
			if(!itemsUnChecked[i]) {
				checkedContacts.add(contacts.get(i));
			}
		}
		
		return checkedContacts;
	}

}