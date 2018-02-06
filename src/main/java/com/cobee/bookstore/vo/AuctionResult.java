package com.cobee.bookstore.vo;

import java.io.Serializable;

public class AuctionResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4754553592476856930L;
	private String userno;
	private String isbn;
	private Integer quality;
	private Integer leftQuality;

	public Integer getLeftQuality() {
		return leftQuality;
	}

	public void setLeftQuality(Integer leftQuality) {
		this.leftQuality = leftQuality;
	}

	public String getUserno() {
		return userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

}
