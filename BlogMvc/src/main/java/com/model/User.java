package com.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
/* @Inheritance(strategy=InheritanceType.SINGLE_TABLE) */
@Table(name = "User_Table")
/*
 * @DiscriminatorColumn(name="User_Type",
 * discriminatorType=DiscriminatorType.STRING)
 */
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userid;
	private String username;
	private String password;
	@Column(unique = true)
	private String email;

	@OneToMany(mappedBy = "user")
	private Collection<Blog> blog = new ArrayList<>();

	public Integer getUserid() {
		return userid;
	}

	public User(Integer userid, String username, String password, String email, Collection<Blog> blog) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.blog = blog;
	}

	public Collection<Blog> getBlog() {
		return blog;
	}

	public void setBlog(Collection<Blog> blog) {
		this.blog = blog;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", blog=" + blog + "]";
	}

	public User(int user_id) {
		super();
		this.userid = user_id;
	}

	public User(int user_id, String user_name, String password, String email) {
		super();
		this.userid = user_id;
		this.username = user_name;
		this.password = password;
		this.email = email;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String user_name, String password, String email) {
		super();
		this.username = user_name;
		this.password = password;
		this.email = email;
	}

}
