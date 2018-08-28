package com.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.Comments;
import com.model.User;

public class BlogDto {

	private int blogid;

	public int getBlogid() {
		return blogid;
	}

	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}

	@NotBlank(message = "Java or Dotnet type is required")
	private String type;
	@NotBlank(message = "Here goes Title of Blog.. Cannot be Blank")
	private String blogTitle;
	private Date blogDate;

	private int tcomment = 0;
	private int rateCount = 0;

	public int getRateCount() {
		return rateCount;
	}

	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}

	public int getTcomment() {
		return tcomment;
	}

	public void setTcomment(int tcomment) {
		this.tcomment = tcomment;
	}

	@NotBlank(message = "Here goes Blog.. Cannot be Blank")
	private String blog;

	@Column(columnDefinition = "DECIMAL(7,2)")
	private float rating;

	@OneToMany(mappedBy = "comments")
	private Collection<CommentsDto> comments = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserDto user;

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Collection<CommentsDto> getComments() {
		return comments;
	}

	public void setComments(Collection<CommentsDto> comments) {
		this.comments = comments;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getBlogDate() {
		return blogDate;
	}

	public void setBlogDate(Date blogDate) {
		this.blogDate = blogDate;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

}
