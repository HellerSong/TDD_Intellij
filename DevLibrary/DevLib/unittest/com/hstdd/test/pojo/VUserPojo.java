package com.hstdd.test.pojo;

import java.sql.Timestamp;

public class VUserPojo {
	// UserPojo part
	private int userId;
	private String userName;
	private int userTypeId;
	private boolean isMarried;
	private Timestamp birthday;

	// UserTypePojo part
	private String userTypeName;


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public boolean isMarried() {
		return isMarried;
	}

	public void setMarried(boolean married) {
		isMarried = married;
	}

	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
}
