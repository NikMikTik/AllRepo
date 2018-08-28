package com.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.repository.query.Param;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.BlogDto;
import com.dto.CommentsDto;
import com.dto.UserDto;
import com.exception.BlogException;
import com.exception.BlogException;
import com.model.Blog;
import com.model.Comments;
import com.model.User;

public interface BlogService {
	/*
	 * List<Comments> findCommentsById(@Param("blogid") int blogid);
	 */ public boolean RegisterB(BlogDto bb);

	public boolean DelB(BlogDto bb);

	public boolean DelC(CommentsDto cdo2);

	List<Blog> findAllById(@Param("blogid") int blogid);

	void updateTComment(@Param("blogid") int blogid, @Param("commentn") int commentn);

	public boolean RegisterC(CommentsDto cc);

	public List<BlogDto> findByBlogid(int blogid) throws BlogException;

	public List<BlogDto> fetchJavaBlog() throws BlogException;

	void updateBlog(@Param("id") int blogid, @Param("blog") String blog);

	int countBlogById(@Param("userid") int userid);

	public List<BlogDto> fetchAuthorBlog(String Author) throws BlogException;

	public List<BlogDto> fetchCategoryAuthorBlog(String Author, String type) throws BlogException;

	public List<BlogDto> fetchDotNetBlog() throws BlogException;

	public List<BlogDto> fetchAllBlog() throws BlogException;

	public List<CommentsDto> fetchAllComments();

	public Blog findByblogid(int blogid);

	public ModelAndView funcUpdateBlog(@ModelAttribute BlogDto blogDto) throws BlogException;

	public ModelAndView funcSubmitBlog(int userid, BlogDto blogDto) throws BlogException;

	public ModelAndView funcDeleteBlog(int blogid);

	public ModelAndView funcOpenBlog(ModelMap modelMap, @RequestParam int blogid) throws BlogException;

	public ModelAndView funcShowRatedCommentedBlog(ModelMap modelMap, int blogid, HttpSession session) throws BlogException;

	public ModelAndView funcupdateRateComment(BlogDto blogDto, CommentsDto commentsDto, float rate);

	public ModelAndView funcEditingBlog(ModelMap modelMap, BlogDto blogDto) throws BlogException;

	public Blog createBlog(BlogDto blogDto);

	public Blog updatedBlog(BlogDto blogDto) throws BlogException;

	public boolean deletedBlog(BlogDto blogDto);

	public boolean deletedBlogUsingId(int blogid);

	public String rateCommentedBlog(BlogDto blogDto);

}
/*
 * # Enabling H2 Console
 * 
 * spring.h2.console.enabled=true
 * 
 * #spring.jpa.hibernate.use-new-id-generator-mappings=false
 * spring.datasource.url=jdbc:h2:mem:testdb;MODE=MYSQL;DB_CLOSE_ON_EXIT=FALSE;
 * DB_CLOSE_DELAY=-1. spring.datasource.username=sa spring.datasource.password=
 * spring.datasource.driver-class-name=org.h2.Driver
 * spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.H2Dialect
 * spring.jpa.hibernate.ddl-auto=update
 */