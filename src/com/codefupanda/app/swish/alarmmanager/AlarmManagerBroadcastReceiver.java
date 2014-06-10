package com.codefupanda.app.swish.alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.codefupanda.app.swish.dao.Dao;
import com.codefupanda.app.swish.util.NotificationUtil;

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

	private Dao dao;
	
	public AlarmManagerBroadcastReceiver() {
		dao = new Dao();
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Swish started! Message : " + dao.getSmsText(context), Toast.LENGTH_LONG).show();
		NotificationUtil.showNotification(context);
	}
	
}
