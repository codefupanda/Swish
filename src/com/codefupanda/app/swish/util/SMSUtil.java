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
package com.codefupanda.app.swish.util;

import java.util.List;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.codefupanda.app.swish.entity.Contact;

/**
 * A Util for sending SMS.
 * 
 * @author Shashank
 */
public class SMSUtil {
	
	/**
	 * Send sms
	 * @param context the context
	 * @param contacts list of contacts 
	 * @param smsText the smsText
	 */
	public static void sendSms(Context context, List<Contact> contacts, String smsText) {
		// if no contacts 
		if(contacts == null || contacts.size() == 0 || smsText == null){
			return ;
		}
		
		SmsManager manager = SmsManager.getDefault();
		for(Contact contact: contacts) {
			
			// if we don't have phone numbers
			if (contact.getPhoneNumbers() == null
					|| contact.getPhoneNumbers().size() == 0) {
				continue;
			}
			
			String text = smsText;
			
			if(contact.getName() != null) {
				text = smsText.replace("%name%", contact.getName());				
			}
			
			// Send to all numbers?!
			for(String phoneNumbers: contact.getPhoneNumbers()) {
				manager.sendTextMessage(phoneNumbers, null, text, null, null);
			}
			Toast.makeText(context, "SMS Sent to " + contact.getName(),
					Toast.LENGTH_LONG).show();
		}
	}
}
