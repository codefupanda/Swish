package com.codefupanda.app.swish.util;

import java.util.List;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.codefupanda.app.swish.entity.Contact;

public class SMSUtil {
	
	public static void sendSms(Context context, List<Contact> contacts, String smsText) {
		SmsManager manager = SmsManager.getDefault();
		for(Contact contact: contacts) {
			String text = smsText.replace("%name%", contact.getName());
			for(String phoneNumbers: contact.getPhoneNumbers()) {
				manager.sendTextMessage(phoneNumbers, null, text, null, null);
			}
			Toast.makeText(context, "SMS Sent to " + contact.getName(),
					Toast.LENGTH_LONG).show();
		}
	}
}
