package com.codefupanda.app.swish.entity;

import java.util.Date;
import java.util.List;


/**
 * Will hold contact details.
 * 
 * @author Shashank
 */
public class Contact {
	
	private String id;
	private String name;
	private int profilePic;
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
	public Contact(String name, int profilePic, Date dateOfBirth) {
		this.name = name;
		this.profilePic = profilePic;
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
	 * @return the profilePic
	 */
	public int getProfilePic() {
		return profilePic;
	}

	/**
	 * @param profilePic the profilePic to set
	 */
	public void setProfilePic(int profilePic) {
		this.profilePic = profilePic;
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