package com.restController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dto.BlogDto;
import com.dto.CommentsDto;
import com.dto.UserDto;
import com.exception.BlogException;
import com.model.Blog;
import com.model.User;
import com.repository.BlogRepository;
import com.repository.UserRepository;
import com.service.IBlogService;
import com.service.IRedisService;
import com.service.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class RestBlogController {

	Logger logger = LoggerFactory.getLogger(RestBlogController.class);

	@Autowired
	IBlogService blogService;

	@Autowired
	IRedisService iredis;

	
	@Autowired
	BlogRepository blogRepository;

	protected ModelMapper modelMapper = new ModelMapper();
	ModelAndView modelAndView = new ModelAndView();

	@RequestMapping(value = "/allBlog", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<BlogDto> retrieveBlogs() throws BlogException {
		return blogService.fetchAllBlog();
	}

	@RequestMapping(value = "/java", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<BlogDto> retrieveJavaBlogs() throws BlogException {
		return blogService.fetchJavaBlog();
	}

	@RequestMapping(value = "/dotnet", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<BlogDto> retrieveDotnetBlogs() throws BlogException {
		return blogService.fetchDotNetBlog();
	}

	@RequestMapping(value = "/all/author/{Author}")
	@ResponseStatus(value = HttpStatus.OK)
	public List<BlogDto> retrieveAllAuthorsBlogs(@PathVariable("Author") String Author) throws BlogException {
		return blogService.fetchAuthorBlog(Author);
	}

	@RequestMapping(value = "/{catalogue}/{Author}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<BlogDto> retrieveCategoryAuthorsBlogs(@PathVariable("catalogue") String catalogue,
			@PathVariable("Author") String Author) throws BlogException {
		return blogService.fetchCategoryAuthorBlog(Author, catalogue);
	}

	@RequestMapping(value = "/blog/{blogid}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<BlogDto> retrieveBlogUsingId(@PathVariable("blogid") int blogid) throws BlogException {
		return blogService.findByBlogid(blogid);
	}

	@RequestMapping(value = "/createBlog", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Blog createBlog(@RequestBody BlogDto blogDto, HttpServletRequest request, HttpServletResponse response)
			throws BlogException {
		Object token = iredis.getValue(request.getHeader("Authorization"));
		if (token == null) {
			throw new BlogException("You are not allowed to update the profile");
		} else {
		Blog createdBlog = this.blogService.createBlog(blogDto);
		if (createdBlog == null) {
			throw new BlogException("Cannot Create New Blog");
		} else {
			logger.info("New Blog Created");
			return createdBlog;
		}}

	}

	@RequestMapping(value = "/updateBlog", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public Blog updateBlog(@RequestBody BlogDto blogDto, HttpServletRequest request, HttpServletResponse response)
			throws BlogException {
		Object token = iredis.getValue(request.getHeader("Authorization"));
		if (token == null) {
			throw new BlogException("You are not allowed to update the profile");
		} else {Blog updatedBlog = this.blogService.updatedBlog(blogDto);
		if (updatedBlog == null) {
			throw new BlogException("Cannot update Blog");
		} else {
			logger.info("Blog Updated");
			return updatedBlog;
		}}

	}

	@RequestMapping(value = "/deleteBlog", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.CREATED)
	public String deleteBlog(@RequestBody BlogDto blogDto, HttpServletRequest request, HttpServletResponse response)
			throws BlogException {
		Object token = iredis.getValue(request.getHeader("Authorization"));
		if (token == null) {
			throw new BlogException("You are not allowed to update the profile");
		} else {boolean deletedBlog = this.blogService.deletedBlog(blogDto);
		if (deletedBlog == false) {
			throw new BlogException("Cannot Delete Blog No:" + blogDto.getBlogid()
					+ " Either It does Not Exist or It is Already Deleted ");
		} else {
			logger.info("Blog No: " + blogDto.getBlogid() + " Deleted");
			return "Blog No: " + blogDto.getBlogid() + " Deleted";
		}}

	}

	@RequestMapping(value = "/deleteBlog/{blogid}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.CREATED)
	public String deleteBlogUsingId(@PathVariable("blogid") int blogid, HttpServletRequest request, HttpServletResponse response) throws BlogException {
		boolean deletedBlogUsingId = this.blogService.deletedBlogUsingId(blogid);
		Object token = iredis.getValue(request.getHeader("Authorization"));
		if (token == null) {
			throw new BlogException("You are not allowed to update the profile");
		} else {if (deletedBlogUsingId == false) {
			throw new BlogException(
					"Cannot Delete Blog No:" + blogid + " Either It does Not Exist or It is Already Deleted ");
		} else {
			logger.info("Blog No: " + blogid + " Deleted");
			return "Blog No: " + blogid + " Deleted";
		}
		}
	}

	@RequestMapping(value = "/rateCommentBlog", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public String rateCommentBlog(@RequestBody BlogDto blogDto, HttpServletRequest request, HttpServletResponse response)
			throws BlogException {
		String rateCommentedBlog = this.blogService.rateCommentedBlog(blogDto);
		if (rateCommentedBlog == null) {
			throw new BlogException("Cannot update Blog");
		} else {
			logger.info("Blog Updated");
			return rateCommentedBlog;
		}

	}

}
