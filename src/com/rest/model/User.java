package com.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	@JsonProperty("UserName")
	String UserName;
	@JsonProperty("Password")
	String Password;
	@JsonProperty("EmailID")
	String EmailID;
	@JsonProperty("ClientUID")
	String ClientUID;
	@JsonProperty("UserID")
	int UserID;
	
	public User(String UserName,String Password,String EmailID,String ClientUID,int UserID){
		this.UserName = UserName;
		this.Password = Password;
		this.EmailID = EmailID;
		this.ClientUID = ClientUID;
		this.UserID = UserID;
	}
	
	public String getUserName() {
		return UserName;
	}
	
	public void setUserName(String userName) {
		this.UserName = userName;
	}

	public String getEmailId() {
		return EmailID;
	}

	public void setEmailId(String emailId) {
		this.EmailID = emailId;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		this.Password = password;
	}
	
	public int getUserID(){
		return UserID;
	}
	public String getEmailID(){
		return EmailID;
	}
	public String getClientUID(){
		return ClientUID;
	}
}

