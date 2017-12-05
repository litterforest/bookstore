package com.cobee.bookstore.entity;

import java.io.Serializable;

public class Tuser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2047074732579507039L;
	
	private Integer uname;
	private String pwd;
	private Double account;
	private String role;

	public Tuser() {
		super();
	}

	public Tuser(Integer uname, String pwd, Double account, String role) {
		super();
		this.uname = uname;
		this.pwd = pwd;
		this.account = account;
		this.role = role;
	}

	public Integer getUname() {
		return uname;
	}

	public void setUname(Integer uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Double getAccount() {
		return account;
	}

	public void setAccount(Double account) {
		this.account = account;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
