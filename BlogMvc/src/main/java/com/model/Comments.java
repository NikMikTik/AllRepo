package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Comment_Table")
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@JoinColumn(name = "blogid")
	private Blog blog;

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

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	@Override
	public String toString() {
		return "Comments [commentid=" + commentid + ", comment=" + comment + ", rName=" + rName + ", rEmail=" + rEmail
				+ ", blog=" + blog + "]";
	}

	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comments(String comment, String rName, String rEmail, Blog blog) {
		super();
		this.comment = comment;
		this.rName = rName;
		this.rEmail = rEmail;
		this.blog = blog;
	}

	public Comments(int commentid, String comment, String rName, String rEmail, Blog blog) {
		super();
		this.commentid = commentid;
		this.comment = comment;
		this.rName = rName;
		this.rEmail = rEmail;
		this.blog = blog;
	}

}
