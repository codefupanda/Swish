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
package com.codefupanda.app.swish.entity;

import java.util.Date;
import java.util.List;

import android.net.Uri;


/**
 * Will hold contact details.
 * 
 * @author Shashank
 */
public class Contact {
	
	private String id;
	private String name;
	private Uri profilePicUri;
	private Date dateOfBirth;
	private List<String> phoneNumbers;
	
	/**
	 * Default constructor.
	 */
	public Contact() {
	}
	
	/**
	 * @param name
	 * @param profilePic
	 * @param dateOfBirth
	 */
	public Contact(String name, Uri profilePicUri, Date dateOfBirth) {
		this.name = name;
		this.profilePicUri = profilePicUri;
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the profilePicUri
	 */
	public Uri getProfilePicUri() {
		return profilePicUri;
	}

	/**
	 * @param profilePicUri the profilePicUri to set
	 */
	public void setProfilePicUri(Uri profilePicUri) {
		this.profilePicUri = profilePicUri;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the phoneNumbers
	 */
	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	/**
	 * @param phoneNumbers the phoneNumbers to set
	 */
	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}