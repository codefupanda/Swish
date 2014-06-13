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

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.codefupanda.app.swish.alarmmanager.AlarmManagerBroadcastReceiver;

/**
 * A Util for setting alarms.
 * 
 * @author Shashank
 */
public class AlarmUtil {
	
	final public static String ONE_TIME = "onetime";
	private static final long INTERVAL =  24 * 60 * 60 * 1000; // Everyday
	
	/**
	 * Set alarm 
	 * @param context
	 */
	public static void SetAlarm(final Context context, final int hour, final int min) {
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		intent.putExtra(ONE_TIME, Boolean.FALSE);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		calendar.add(Calendar.HOUR, hour);
		calendar.add(Calendar.MINUTE, min);
		
		am.setRepeating(AlarmManager.RTC_WAKEUP, 
					Calendar.getInstance().getTimeInMillis(),
					INTERVAL, pi);
	}

	/**
	 * cancel alarm.
	 * 
	 * @param context
	 */
	public static void CancelAlarm(Context context) {
		Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		PendingIntent sender = PendingIntent
				.getBroadcast(context, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(sender);
	}

	/**
	 * One time alarm.
	 * 
	 * @param context
	 */
	public static void setOnetimeTimer(Context context) {
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		intent.putExtra(ONE_TIME, Boolean.TRUE);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
	}
}
