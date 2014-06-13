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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;

import com.codefupanda.app.swish.exception.SwishException;

/**
 * Used for IO operations.
 * 
 * @author Shashank
 */
public class Dao {

	private static final String FILE_NAME = "smstext.txt";
	private static final String DEFUALT_SMS_TEXT = "Hi, A very happy birthday";
	private static final String FILE_SEND_TIME_NAME = "sendtime.txt";
	private static final int[] DEFUALT_SMS_TIME = {24, 00};

	/**
	 * Get the SMS text.
	 * @param context the context
	 * @return sms text
	 */
	public String getSmsText(final Context context) {
		File file = new File(context.getFilesDir().getPath().toString() + FILE_NAME);
		StringBuilder message = new StringBuilder();
		if (file.exists()) {
			BufferedReader input = null;
			try {
				input = new BufferedReader(new InputStreamReader(
						new FileInputStream(file)));
				String line;
				while ((line = input.readLine()) != null) {
					message.append(line);
				}
				return message.toString();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return DEFUALT_SMS_TEXT;
	}

	/**
	 * Save the SMS text 
	 * @param context the context
	 * @param smsText text
	 * @throws SwishException if we could not save text
	 */
	public void saveSmsText(final Context context, final String smsText) throws SwishException {
		File file = new File(context.getFilesDir().getPath().toString() + FILE_NAME);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			writer.write(smsText);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SwishException();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Save the Notification time.
	 * 
	 * @param context
	 * @param currentHour
	 * @param currentMinute
	 * @throws SwishException
	 */
	public void saveSendTime(final Context context, final Integer currentHour, final Integer currentMinute) throws SwishException {
		File file = new File(context.getFilesDir().getPath().toString() + FILE_SEND_TIME_NAME);
		BufferedWriter writer = null;
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			
			// if minutes is less then 10, converted string will be of single char
			// Making sure that it's always two characters.
			String currentMinuteString = null;
			if(currentMinute < 10) {
				currentMinuteString  = "0" + currentMinute;
			} else {
				currentMinuteString = String.valueOf(currentMinute);
			}
			
			String currentHourString = null;
			if(currentHour < 10) {
				currentHourString  = "0" + currentHour;
			} else {
				currentHourString = String.valueOf(currentHour);
			}
			writer.write(currentHourString + ":" + currentMinuteString);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SwishException();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Get the notification time.
	 * 
	 * @param context
	 * @return notification time
	 */
	public int[] getSendTime(final Context context) {
		int[] result = DEFUALT_SMS_TIME;
		File file = new File(context.getFilesDir().getPath().toString() + FILE_SEND_TIME_NAME);
		if (file.exists()) {
			BufferedReader input = null;
			try {
				input = new BufferedReader(new InputStreamReader(
						new FileInputStream(file)));
				String line = input.readLine();
				if(line!= null && line.length() == 5) {
					result[0] = Integer.valueOf(line.substring(0, 2));
					result[1] = Integer.valueOf(line.substring(3, 5));
				}
				return result;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return DEFUALT_SMS_TIME;
	}

}
