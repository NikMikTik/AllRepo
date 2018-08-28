package com.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.Blog;

public class CommentsDto {

	private int commentid;
	private String comment;
	private String rName;
	private String rEmail;
	
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getrEmail() {
		return rEmail;
	}
	public void setrEmail(String rEmail) {
		this.rEmail = rEmail;
	}
	@ManyToOne
	@JoinColumn(name="comments")
	private BlogDto blog;
	
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public BlogDto getBlog() {
		return blog;
	}
	public void setBlog(BlogDto blog) {
		this.blog = blog;
	}
	
}
