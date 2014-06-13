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
package com.codefupanda.app.swish.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;

import com.codefupanda.app.swish.entity.Contact;

/**
 * Fetch Birthdays from the device.
 *  
 * @author Shashank
 */
public class BirthdayContactDao {
	
	private static final String YYYY_MM_DD = "yyyy-MM-dd";
	private static final String __MM_DD = "--MM-dd";
	
	private Context context;
	
	public BirthdayContactDao(Context context) {
		this.context = context;
	}
	
	/**
	 * Get contacts whose birthday is today.
	 * 
	 * @return list of contacts contacts whose birthday is today
	 */
	@SuppressLint("SimpleDateFormat")
	public List<Contact> getTodaysBirthdayContacts() {
		List<Contact> contacts = new ArrayList<Contact>();
		
		Cursor cursor = getContactsBirthdays();
        int idIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.CONTACT_ID);
        int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        int startDateIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE);
        
        Contact contact = null;
    	DateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
    	DateFormat sdfMMDD = new SimpleDateFormat(__MM_DD);
    	
    	Calendar cal = Calendar.getInstance();
    	
    	int tMonth = cal.get(Calendar.MONTH);
    	int tDate = cal.get(Calendar.DAY_OF_MONTH);
    	
        while (cursor.moveToNext()) {
			try {
				contact = new Contact();
				String dateString = cursor.getString(startDateIndex);
				if(dateString.length() == YYYY_MM_DD.length()) {
					cal.setTimeInMillis(sdf.parse(dateString).getTime());
				} else {
					cal.setTimeInMillis(sdfMMDD.parse(dateString).getTime());
				}
				if(tMonth != cal.get(Calendar.MONTH) || 
						tDate != cal.get(Calendar.DAY_OF_MONTH)) {
					continue;
				}
            	contact.setId(cursor.getString(idIndex));
            	contact.setPhoneNumbers(getPhoneNumbers(cursor.getString(idIndex)));
            	contact.setName(cursor.getString(nameIndex));
            	
            	Uri person = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long
                        .parseLong(cursor.getString(idIndex)));
            	if(person != null) {
            		Uri photoUri = Uri.withAppendedPath(person, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
            		contact.setProfilePicUri(photoUri);
            	}
            	
            	contacts.add(contact);
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				cursor.close();
			}
        }
        return contacts;
	}
	
	/**
	 * A helper method to retrieve phone numbers of contacts.
	 * 
	 * @param contactId whose phone number is required
	 * 
	 * @return list of phone numbers
	 */
	private List<String> getPhoneNumbers(String contactId) {
		List<String> phoneNo = new ArrayList<String>();
		
		Cursor cursor = context.getContentResolver().query(
				CommonDataKinds.Phone.CONTENT_URI,
				null,
				CommonDataKinds.Phone.CONTACT_ID + " = ?",
				new String[]{contactId},
				null);
		while(cursor.moveToNext()) {
			phoneNo.add(cursor.getString(cursor.getColumnIndex(CommonDataKinds.Phone.NUMBER)));
		}
		return phoneNo;
	}
	
	/**
	 * Helper method to retrieve contacts.
	 * 
	 * @return cursor
	 */
	private Cursor getContactsBirthdays() {
		Uri uri = ContactsContract.Data.CONTENT_URI;

		String[] projection = new String[] {
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Event.CONTACT_ID,
				ContactsContract.CommonDataKinds.Event.START_DATE,
			};

		String where = ContactsContract.Data.MIMETYPE + "= ? AND "
				+ ContactsContract.CommonDataKinds.Event.TYPE + "="
				+ ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY;
		String[] selectionArgs = new String[] { ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE };
		String sortOrder = null;
		return context.getContentResolver().query(uri, projection, where, selectionArgs, sortOrder);
	}
	
}
