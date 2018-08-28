package com.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Blog_Table")
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int blogid;
	private String blogTitle;
	private String type;
	private Date blogDate;
	private int rateCount = 0;

	public int getRateCount() {
		return rateCount;
	}

	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}

	private String blog;

	@Column(columnDefinition = "DECIMAL(7,2)")
	private float rating = 0f;
	private int tcomment = 0;

	@OneToMany(mappedBy = "blog")
	private Collection<Comments> comments = new ArrayList<>();

	public Collection<Comments> getComments() {
		return comments;
	}

	public void setComments(Collection<Comments> comments) {
		this.comments = comments;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTcomment() {
		return tcomment;
	}

	public void setTcomment(int tcomment) {
		this.tcomment = tcomment;
	}

	public Blog() {
		super();
	}

	public int getBlogid() {
		return blogid;
	}

	public void setBlogid(int blogid) {
		this.blogid = blogid;
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

	/*
	 * @Override public String toString() { return "Blog [blogid=" + blogid +
	 * ", blogTitle=" + blogTitle + ", type=" + type + ", blogDate=" + blogDate +
	 * ", rateCount=" + rateCount + ", blog=" + blog + ", rating=" + rating +
	 * ", tcomment=" + tcomment + ", comments=" + comments + ", user=" + user + "]";
	 * }
	 */

	@Override
	public String toString() {
		return "Blog [blogid=" + blogid + ", blogTitle=" + blogTitle + ", type=" + type + ", blogDate=" + blogDate
				+ ", rateCount=" + rateCount + ", blog=" + blog + ", rating=" + rating + ", tcomment=" + tcomment
				+ ", comments=" + comments + ", user=" + user + "]";
	}

	public Blog(int blogid, String blogTitle, String type, Date blogDate, int rateCount, String blog, float rating,
			int tcomment, Collection<Comments> comments, User user) {
		super();
		this.blogid = blogid;
		this.blogTitle = blogTitle;
		this.type = type;
		this.blogDate = blogDate;
		this.rateCount = rateCount;
		this.blog = blog;
		this.rating = rating;
		this.tcomment = tcomment;
		this.comments = comments;
		this.user = user;
	}

	public Blog(Comments c, User u, String blogType, String blogTitle, String blog) {
		this.blogTitle = blogTitle;
		this.type = blogType;
		this.blog = blog;
		this.comments = (Collection<Comments>) c;
		this.user = u;
	}

	public static Comparator<Blog> blogIdComp = new Comparator<Blog>() {

		public int compare(Blog s1, Blog s2) {

			int r1 = s1.getBlogid();
			int r2 = s2.getBlogid();

			/* For ascending order */
			return r1 - r2;

			/* For descending order */
			// rollno2-rollno1;
		}
	};

}
