package com.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.BlogDto;
import com.dto.CommentsDto;
import com.dto.UserDto;
import com.exception.BlogException;
import com.model.Blog;
import com.model.Comments;
import com.model.User;
import com.repository.BlogRepository;
import com.repository.UserRepository;
import com.service.BlogService;
import com.service.BlogServiceImpl;
import com.service.UserServiceImpl;

@Controller
@RequestMapping("/Blog")
public class BlogController {

	Logger logger = LoggerFactory.getLogger(BlogController.class);

	@Autowired
	BlogService blogService;

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserServiceImpl userServiceImpl;

	protected ModelMapper modelMapper = new ModelMapper();
	ModelAndView modelAndView = new ModelAndView();

	@PostMapping("/open")
	public String openBlog(ModelMap modelMap, @RequestParam int blogid) throws BlogException {
		modelAndView = blogService.funcOpenBlog(modelMap, blogid);
		if (!modelAndView.equals(null)) {
		} else
			throw new BlogException("Sorry, Can't Open this particular Blog");
		return "OpenBlog";
	}

	@RequestMapping(value = "/open/submit/rateComment/{blogid}", method = RequestMethod.GET)
	public String showRatedCommentedBlog(ModelMap modelMap, @PathVariable("blogid") int blogid, HttpSession session)
			throws BlogException {
		modelAndView = blogService.funcShowRatedCommentedBlog(modelMap, blogid, session);
		if (!modelAndView.equals(null)) {
		} else
			throw new BlogException("Sorry, Can't see this particular Rated_Commented Blog");
		return "OpenBlog";
	}

	@RequestMapping("")
	public String welcome(ModelMap modelMap) throws BlogException {
		modelMap.put("userDtoList", userServiceImpl.fetchAllUser());
		modelMap.put("blogDtoList", blogService.fetchAllBlog());
		logger.info("Main Website: Showing All Blogs");
		return "Blog";
	}

	@RequestMapping("/java")
	public String chooseJava(ModelMap modelMap) throws BlogException {
		if (blogService.fetchJavaBlog().isEmpty()) {
			throw new BlogException("Sorry, No Java Blogs");
		} else {
			modelMap.put("userDtoList", userServiceImpl.fetchAllUser());
			modelMap.put("blogDtoList", blogService.fetchJavaBlog());
			logger.info("Showing All Java Blogs");
			return "chooseJ";
		}
	}

	@RequestMapping("/dotNet")
	public String chooseDotNet(ModelMap modelMap) throws BlogException {
		if (blogService.fetchDotNetBlog().isEmpty()) {
			throw new BlogException("Sorry, No DotNet Blogs");
		} else {
			modelMap.put("userDtoList", userServiceImpl.fetchAllUser());
			modelMap.put("blogDtoList", blogService.fetchDotNetBlog());
			logger.info("Showing All DotNet Blogs");
			return "chooseD";
		}

	}

	@RequestMapping("/catalogue/author")
	public String chooseAuthor(ModelMap modelMap, @RequestParam String Author, @RequestParam String type,
			HttpServletResponse response) throws BlogException {
		if (Author.equals(null)) {
			return "Blog";
		}
		if (blogService.fetchCategoryAuthorBlog(Author, type).isEmpty()) {
			throw new BlogException("Sorry, No Particular Author's Blogs Present");
		} else {
			modelMap.put("Author", Author);
			modelMap.put("type", type);
			modelMap.put("userDtoList", userServiceImpl.fetchAllUser());
			modelMap.put("blogDtoList", blogService.fetchCategoryAuthorBlog(Author, type));
			logger.info("Showing catalogue Blogs along with particular Author");
			return "chooseA";
		}
	}

	@RequestMapping("/all/author")
	public String chooseAllBlogs(ModelMap modelMap, @RequestParam String Author) throws BlogException {
		if (Author.equals(null)) {
			return "Blog";
		}
		if (blogService.fetchAuthorBlog(Author).isEmpty()) {
			throw new BlogException("Sorry, There are no Blogs belonging to this Author");
		} else {
			modelMap.put("userDtoList", userServiceImpl.fetchAllUser());
			modelMap.put("blogDtoList", blogService.fetchAuthorBlog(Author));
			modelMap.put("Author", Author);
			logger.info("Showing All Blogs along with particular Author");
			return "chooseAB";
		}
	}

	@PostMapping("/open/rateComment")
	public ModelAndView updateRateComment(@ModelAttribute BlogDto blogDto, @ModelAttribute CommentsDto commentsDto,
			@RequestParam("rate") float rate) throws BlogException {
		modelAndView = blogService.funcupdateRateComment(blogDto, commentsDto, rate);
		if (!modelAndView.equals(null)) {
			/*modelAndView.setViewName(redirect:/Blog/open/submit/rateComment/" + blogid);
*/		} else {
			modelAndView.setViewName("Blog");
			throw new BlogException("Sorry, Can't see this particular Rated_Commented Blog");
		}
		return new ModelAndView("redirect:/Blog/open/submit/rateComment/" + blogDto.getBlogid());
	}

}
