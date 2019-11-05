package com.po;

public class User {
	private String uname;
	private String password;
	private String repassword;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	@Override
	public String toString() {
		return "User [uname=" + uname + ", password=" + password + ", repassword=" + repassword + "]";
	}
	
	
}
