package com.cobee.bookstore.entity;

import java.io.Serializable;

public class Tbuydetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6340606458761869274L;

	private Integer autoid;
	private String isbn;
	private Double buyid;
	private Double bcount;

	public Tbuydetail() {
		super();
	}

	public Tbuydetail(Integer autoid, String isbn, Double buyid, Double bcount) {
		super();
		this.autoid = autoid;
		this.isbn = isbn;
		this.buyid = buyid;
		this.bcount = bcount;
	}

	public Integer getAutoid() {
		return autoid;
	}

	public void setAutoid(Integer autoid) {
		this.autoid = autoid;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Double getBuyid() {
		return buyid;
	}

	public void setBuyid(Double buyid) {
		this.buyid = buyid;
	}

	public Double getBcount() {
		return bcount;
	}

	public void setBcount(Double bcount) {
		this.bcount = bcount;
	}

}
