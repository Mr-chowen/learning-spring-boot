package com.pojo;
/**
 * 	数据库demo中的idcard的持久类
 * @author kevin
 *
 */
public class Idcard {
	private Integer id;
	private String code;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "Idcard [id=" + id + ", code=" + code + "]";
	}
	
	
}
