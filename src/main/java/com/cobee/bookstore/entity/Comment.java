package com.cobee.bookstore.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7055737964986260929L;
	private String commentID;
	private String isbn;
	private String commentContent;
	private Date createDate;
	// 评论人的ID
	private String commenterID;
	// 评论人的名字
	private String commenterName;
	// 点赞数量
	private Integer thumbsupCount;
	
	public String getCommentID() {
		return commentID;
	}

	public void setCommentID(String commentID) {
		this.commentID = commentID;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCommenterID() {
		return commenterID;
	}

	public void setCommenterID(String commenterID) {
		this.commenterID = commenterID;
	}

	public String getCommenterName() {
		return commenterName;
	}

	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}

	public Integer getThumbsupCount() {
		return thumbsupCount;
	}

	public void setThumbsupCount(Integer thumbsupCount) {
		this.thumbsupCount = thumbsupCount;
	}
	
}
