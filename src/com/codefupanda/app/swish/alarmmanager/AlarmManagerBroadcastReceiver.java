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
package com.codefupanda.app.swish.alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.codefupanda.app.swish.dao.BirthdayContactDao;
import com.codefupanda.app.swish.util.NotificationUtil;

/**
 * Show notifications. 
 *  
 * @author Shashank
 */
public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {
	
	/**
	 * If contacts have birthday display the notification.
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		BirthdayContactDao dao = new BirthdayContactDao(context);
		
		if(dao.getTodaysBirthdayContacts().size() != 0) {
			NotificationUtil.showNotification(context);			
		}
	}
	
}
