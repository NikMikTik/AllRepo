package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.BlogDto;
import com.exception.BlogException;
import com.exception.BlogException;
import com.model.Blog;
import com.model.User;
import com.repository.BlogRepository;
import com.repository.UserRepository;
import com.service.BlogService;
import com.service.BlogServiceImpl;
import com.service.UserService;
import com.service.UserServiceImpl;

@Controller
@RequestMapping("/Dashboard")
public class DashboardController {

	Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	BlogServiceImpl blogServiceImpl;

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	BlogService blogService;

	@Autowired
	UserService userService;

	protected ModelMapper modelMapper = new ModelMapper();
	ModelAndView modelAndView = new ModelAndView();

	@RequestMapping("")
	public ModelAndView Dashboard(HttpSession session, ModelMap modelMap) throws BlogException {
		try {
			int userid = (Integer) session.getAttribute("uid");
			String uname = (String) session.getAttribute("uname");
			if (userid == 0 || uname.equals(null)) {
				modelAndView.setViewName("Login");
				throw new BlogException("You are Not an Authorized Person, Login First");

			} else {
				int totalBlogs = blogServiceImpl.countBlogById(userid);
				logger.info("Welcome to Dashboard");
				modelMap.put("totalBlogs", totalBlogs);
				modelAndView.setViewName("Dashboard");
			}
		} catch (Exception e) {

		}
		return modelAndView;
	}

	@RequestMapping("/add/show")
	public ModelAndView showAddedBlog(ModelMap modelMap) throws BlogException, BlogException {
		if (blogService.fetchAllBlog().isEmpty()) {
			modelAndView.setViewName("Dashboard");
			throw new BlogException("Sorry, No Blogs");
		} else {
			logger.info("Showing the Blog you just added");
			modelMap.put("blogDtoList", blogServiceImpl.fetchAllBlog());
			modelMap.put("check_add", "added");
			modelAndView.setViewName("ShowBlog");

		}
		return modelAndView;
	}

	@RequestMapping("/edit/show")
	public ModelAndView showEditedBlog(ModelMap modeMap) throws BlogException, BlogException {
		if (blogService.fetchAllBlog().isEmpty()) {
			modelAndView.setViewName("Dashboard");
			throw new BlogException("Sorry, No Edited Blogs");
		} else {
			modeMap.put("blogDtoList", blogServiceImpl.fetchAllBlog());
			modeMap.put("check_edit", "edited");
			modelAndView.setViewName("ShowBlog");
		}
		return modelAndView;
	}

	@RequestMapping("/view")
	public ModelAndView viewBlogs(ModelMap modelMap, HttpSession session) throws BlogException {
		if (userService.emptyBlogList(session) == false) {
			modelAndView.setViewName("Dashboard");
			throw new BlogException("Sorry, No Blogs to be Shown.. Either you have Deleted all Blogs or you haven't written any Blog..!");
		} else {
			modelMap.put("blogDtoList", blogServiceImpl.fetchAllBlog());
			modelMap.put("userDtoList", userServiceImpl.fetchAllUser());
			modelMap.put("commentsDtoList", blogServiceImpl.fetchAllComments());
			logger.info("Showing the Blog you just edited");
			modelAndView.setViewName("view");
		}
		return modelAndView;
	}

	@RequestMapping("/view/edited")
	public ModelAndView viewEditedBlog(ModelMap modelMap, HttpSession session) throws BlogException {
		if (userService.emptyBlogList(session) == false) {
			modelAndView.setViewName("Dashboard");
			throw new BlogException("Sorry, No Blogs to be Shown.. Either you have Deleted all Blogs or you haven't written any Blog..!");
		} else {
			modelMap.put("commentsDtoList", blogServiceImpl.fetchAllComments());
			modelMap.put("blogDtoList", blogServiceImpl.fetchAllBlog());
			modelMap.put("userDtoList", userServiceImpl.fetchAllUser());
			modelMap.put("edit", "edited");
			logger.info("Showing All Blogs along with the blog you just edited");
			modelAndView.setViewName("view");
		}
		return modelAndView;
	}

	@RequestMapping("/view/deleted")
	public ModelAndView viewDeletedBlog(ModelMap modelMap, HttpSession session) throws BlogException {
		if (userService.emptyBlogList(session) == false) {
			modelAndView.setViewName("Dashboard");
			throw new BlogException("Sorry, Empty BlogList.. You have Deleted All of your Blogs..!");
		} else {
			modelMap.put("commentsDtoList", blogServiceImpl.fetchAllComments());
			modelMap.put("blogDtoList", blogServiceImpl.fetchAllBlog());
			modelMap.put("userDtoList", userServiceImpl.fetchAllUser());
			modelMap.put("del", "deleted");
			logger.info("Showing All Blogs along with the blog you just deleted");
			modelAndView.setViewName("view");
		}
		return modelAndView;
	}

	@PostMapping("/add/submit")
	public ModelAndView submitBlog(@RequestParam int userid, @ModelAttribute BlogDto blogDto)
			throws BlogException {
		modelAndView = blogServiceImpl.funcSubmitBlog(userid, blogDto);
		if (modelAndView.equals(null)) {
			modelAndView.setViewName("Dashboard");
			throw new BlogException("Sorry, Unable to show Added Blog");
		}
		return modelAndView;

		/*
		 * return new ModelAndView("redirect:/Dashboard/add");
		 */ }

	@RequestMapping("/add")
	public String addBlog() {
		return "add";
	}

	@PostMapping("/edit")
	public ModelAndView updateBlog(@ModelAttribute BlogDto blogDto) throws BlogException {
		/* String title = blogDto.getBlogTitle(); */
		/* String blog = blogDto.getBlog(); */
		modelAndView = blogServiceImpl.funcUpdateBlog(blogDto);
		if (modelAndView.equals(null)) {
			modelAndView.setViewName("Dashboard");
			throw new BlogException("Sorry, Unable to Open Edit Page");
		}

		return modelAndView;

	}

	@DeleteMapping(value = "/delete/{blogid}")
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView deleteBlog(@PathVariable("blogid") int blogid, final RedirectAttributes redirectAttributes)
			throws BlogException {
		redirectAttributes.addFlashAttribute("css", "Success");
		redirectAttributes.addFlashAttribute("msg", "The user is deleted");
		modelAndView = blogServiceImpl.funcDeleteBlog(blogid);
		if (modelAndView.equals(null)) {
			modelAndView.setViewName("Dashboard");
			throw new BlogException("Sorry, Unable to Delete Blog");
		}
		return modelAndView;
	}

	@PostMapping(value = "/view/edit/show")
	public ModelAndView editingBlog(ModelMap modelMap, @ModelAttribute BlogDto blogDto) throws BlogException {
		modelAndView = blogServiceImpl.funcEditingBlog(modelMap, blogDto);
		if (modelAndView.equals(null)) {
			modelAndView.setViewName("Dashboard");
			throw new BlogException("Sorry, Unable to Delete Blog");
		}
		return modelAndView;
	}

	@PostMapping("/view/edit")
	public ModelAndView editBlog(@RequestParam("blogid") int blogid, ModelMap modelMap) throws BlogException {
		if (blogRepository.findByBlogid(blogid) == null) {
			modelAndView.setViewName("Dashboard");
			throw new BlogException("Not a Correct Id to get Edited");
		} else {
			Blog blog = blogRepository.findByBlogid(blogid);
			String bt = blog.getBlogTitle();
			String blog1 = blog.getBlog();
			modelMap.put("id", blogid);
			modelMap.put("bt", bt);
			modelMap.put("b", blog1);
			logger.info("Redirect to Edit Page");
			modelAndView.setViewName("EditPage");
		}
		return modelAndView;

	}

	@PostMapping("/delete")
	public ModelAndView showDeletedBlog(@RequestParam int blogid, ModelMap modelMap, HttpSession session)
			throws BlogException {
		if (blogRepository.findByBlogid(blogid) == null) {
			modelAndView.setViewName("Dashboard");
			throw new BlogException("Not a Correct Id to get Deleted");
		} else {
			Blog bb = blogRepository.findByBlogid(blogid);
			BlogDto bdo = modelMapper.map(bb, BlogDto.class);
			blogServiceImpl.DelB(bdo);
			logger.info("deleted");
			int userid = (Integer) session.getAttribute("uid");
			int totalBlogs = blogServiceImpl.countBlogById(userid);
			modelMap.put("del", "deleted");
			modelMap.put("totalBlogs", totalBlogs);
			logger.info("Deleted the New added Blog");
			modelAndView.setViewName("Dashboard");
		}
		return modelAndView;

	}

}
