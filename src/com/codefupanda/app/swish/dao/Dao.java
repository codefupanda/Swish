/**
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
import android.text.Editable;

import com.codefupanda.app.swish.exception.BrithdayWisherException;

/**
 * @author Shashank
 * 
 */
public class Dao {

	private static final String FILE_NAME = "smstext.txt";
	private static final String DEFUALT_SMS_TEXT = "Hi, A very happy birthday";
	private static final String FILE_SEND_TIME_NAME = "sendtime.txt";

	public String getSmsText(Context context) {
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

	public void saveSmsText(Context context, Editable editable) throws BrithdayWisherException {
		File file = new File(context.getFilesDir().getPath().toString() + FILE_NAME);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			writer.write(editable.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BrithdayWisherException();
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

	public void saveSendTime(Context context, Integer currentHour, Integer currentMinute) throws BrithdayWisherException {
		File file = new File(context.getFilesDir().getPath().toString() + FILE_SEND_TIME_NAME);
		BufferedWriter writer = null;
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			writer.write(currentHour + ":" + currentMinute);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BrithdayWisherException();
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
	
	public int[] getSendTime() {
		return null;
	}

}
