package com.po;

import java.util.Arrays;

public class User {
	private String username;
	private String[] hobby;
	private String[] friends;
	private String carrer;
	private String houseRegister;
	private String remark;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	public String[] getFriends() {
		return friends;
	}
	public void setFriends(String[] friends) {
		this.friends = friends;
	}
	public String getCarrer() {
		return carrer;
	}
	public void setCarrer(String carrer) {
		this.carrer = carrer;
	}
	public String getHouseRegister() {
		return houseRegister;
	}
	public void setHouseRegister(String houseRegister) {
		this.houseRegister = houseRegister;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", hobby=" + Arrays.toString(hobby) + ", friends="
				+ Arrays.toString(friends) + ", carrer=" + carrer + ", houseRegister=" + houseRegister + ", remark="
				+ remark + "]";
	}
	
	
}
