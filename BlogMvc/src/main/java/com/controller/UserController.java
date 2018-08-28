package com.controller;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dto.UserDto;
import com.exception.ControllerException;
import com.model.User;
import com.repository.BlogRepository;
import com.repository.UserRepository;
import com.service.BlogServiceImpl;
import com.service.UserServiceImpl;

@Controller
public class UserController {

	Logger logger = LoggerFactory.getLogger(BlogController.class);
	protected ModelMapper modelMapper;
	ModelAndView modelAndView = new ModelAndView();

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	BlogServiceImpl blogServiceImpl;

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	UserRepository userRepository;

	@RequestMapping("/Login")
	public String login(ModelMap modelmap) {
		modelmap.put("check", "false");
		logger.info("Login Page");
		return "Login";
	}

	@RequestMapping("/Logout")
	public String logout() {
		logger.info("Logging out");
		return "Logout";
	}
	/*
	 * @RequestMapping("/ShowUser")
	 * 
	 * @ResponseBody public String showuser() {
	 * logger.info("This is Showing All Users"); String result = ""; for (UserDto
	 * userDto : userServiceImpl.fetchAllUser()) { result += userDto.getUserid() +
	 * userDto.getUsername() + userDto.getPassword() + "</br>"; }
	 * logger.info("Shows All Users"); return result;
	 * 
	 * }
	 */

	@RequestMapping("/SignUp")
	public String registration() {
		logger.info("Welcome to the Registration of User");
		return "SignUp";
	}

	@PostMapping("/SignUp/registered")
	public ModelAndView create(@ModelAttribute UserDto userDto1, ModelMap modelMap) throws ControllerException {
		modelAndView = userServiceImpl.funcCreate(userDto1, modelMap);
		if (modelAndView.equals(null)) {
			modelAndView.setViewName("SignUp");
			throw new ControllerException("Cannot Register the User");
		}
		return modelAndView;

	}

	@PostMapping("/Welcome")
	public String validation(@RequestParam String email, @RequestParam String password, HttpSession session,
			ModelMap modelMap) throws ControllerException {
		if ((userServiceImpl.findByEmailAndPassword(email, password) != null)) {
			int check_valid = 0;
			int userId = 0;
			User u;
			userId = userRepository.findIdByName(email);
			u = userRepository.findByUserid(userId);
			String username = u.getUsername();
			check_valid = 1;
			session.setAttribute("validity", check_valid);
			session.setAttribute("uid", userId);
			session.setAttribute("uname", username);
			session.setAttribute("emailn", email);
			logger.info("Valid User");
			int totalBlogs = blogServiceImpl.countBlogById(userId);
			modelMap.put("totalBlogs", totalBlogs);
			return "Dashboard";
		} else if (userServiceImpl.findByEmail(email) != null) {
			int validity = 0;
			session.setAttribute("validity", validity);
			logger.info("UserName/Password Incorrect");
			modelMap.put("check", "Incorrect");
		} else if (userServiceImpl.findByEmail(email) == null) {
			int validity = 0;
			session.setAttribute("validity", validity);
			logger.info("Not a Valid User, Sign Up Please");
			modelMap.put("check", "NotValid");

		} else {
			throw new ControllerException("Cannot SignIn.. Error Occured");
		}
		return "Login";
	}

}
