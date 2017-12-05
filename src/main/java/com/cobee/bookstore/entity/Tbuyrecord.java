package com.cobee.bookstore.entity;

import java.io.Serializable;
import java.util.Date;

public class Tbuyrecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4143639984725640701L;

	private Integer buyid;
	private String uname;
	private Date buytime;
	private Double allmoney;

	public Tbuyrecord() {
		super();
	}

	public Tbuyrecord(Integer buyid, String uname, Date buytime, Double allmoney) {
		super();
		this.buyid = buyid;
		this.uname = uname;
		this.buytime = buytime;
		this.allmoney = allmoney;
	}

	public Integer getBuyid() {
		return buyid;
	}

	public void setBuyid(Integer buyid) {
		this.buyid = buyid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Date getBuytime() {
		return buytime;
	}

	public void setBuytime(Date buytime) {
		this.buytime = buytime;
	}

	public Double getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(Double allmoney) {
		this.allmoney = allmoney;
	}

}
