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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.codefupanda.app.swish.constant.Constants;
import com.codefupanda.app.swish.dao.Dao;
import com.codefupanda.app.swish.exception.SwishException;

/**
 * Activity for settings screen. 
 *  
 * @author Shashank
 */
public class SettingsActivity extends ActionBarActivity {
	
	/** Success message for updating settings */
	private static final String SAVE_FAILED = "Something went wrong!";
	
	/** Failure message for updating settings */
	private static final String SAVED = "Saved";
	
	/** Dao for saving settings */
	private Dao dao;
	
	/**
	 * Populates the sms text and notification timing.
	 * On click of save button, saves settings.
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        dao = new Dao();
        
        final EditText smsText = (EditText) findViewById(R.id.smsText);
        smsText.setText(dao.getSmsText(getApplicationContext()));
        
        final TimePicker picker = (TimePicker) findViewById(R.id.startTime);
        int time[] = dao.getSendTime(getApplicationContext());
        picker.setIs24HourView(false);
        
        if(time != null) {
        	picker.setCurrentHour(time[0]);
        	picker.setCurrentMinute(time[1]);
        }
        
        final Button button = (Button) findViewById(R.id.startSwishing);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				try {
					dao.saveSmsText(getApplicationContext(), smsText.getText().toString());
					dao.saveSendTime(getApplicationContext(), picker.getCurrentHour(), picker.getCurrentMinute());
					
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
					// Disable flash screen, required only for first run
					intent.putExtra(Constants.SHOW_FLASH, Boolean.FALSE);
					startActivity(intent);
		            finish();
					Toast.makeText(getApplicationContext(), SAVED, Toast.LENGTH_LONG).show();
				} catch (SwishException e) {
					Toast.makeText(getApplicationContext(), SAVE_FAILED, Toast.LENGTH_LONG).show();
				}
			}
		});
    }
    
}
