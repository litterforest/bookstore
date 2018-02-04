package com.cobee.bookstore.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Tbook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4948749122580556369L;

	private String isbn;
	private String bname;
	private String press;
	private Double price;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date pdate;
	private String picurl;
	private Integer pageViews;

	public Tbook() {
		super();
	}

	public Tbook(String isbn, String bname, String press, Double price, Date pdate, String picurl) {
		super();
		this.isbn = isbn;
		this.bname = bname;
		this.press = press;
		this.price = price;
		this.pdate = pdate;
		this.picurl = picurl;
	}
	
	public Integer getPageViews() {
		return pageViews;
	}

	public void setPageViews(Integer pageViews) {
		this.pageViews = pageViews;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
}
