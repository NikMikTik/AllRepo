package com.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dto.BlogDto;
import com.dto.CommentsDto;
import com.dto.UserDto;
import com.exception.BlogException;
import com.exception.BlogException;
import com.model.Blog;
import com.model.Comments;
import com.model.User;
import com.repository.BlogRepository;
import com.repository.CommentsRepository;
import com.repository.UserRepository;
import com.sun.javafx.scene.traversal.Direction;

@Service
@Transactional
public class BlogServiceImpl implements IBlogService {

	protected ModelMapper modelMapper = new ModelMapper();
	Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);

	protected Blog blg;
	protected BlogDto blogDto;

	protected Comments cmnt;
	protected CommentsDto commentsDto;

	@Autowired
	private IUserService userService;

	@Autowired
	private CommentsRepository commentsRepository;

	@Autowired
	private BlogRepository blogRepository;

	public BlogServiceImpl(BlogRepository br) {
		this.blogRepository = br;
	}

	@Autowired
	private UserRepository userRepository;

	BlogServiceImpl bsi;

	public List<BlogDto> fetchJavaBlog() throws BlogException {
		List<BlogDto> blogDtolist = new ArrayList<>();
		List<Blog> blogs = blogRepository.findAll();
		for (Blog blog : blogs) {
			blogDto = modelMapper.map(blog, BlogDto.class);
			if (blogDto.getType().equalsIgnoreCase("JAVA"))
				blogDtolist.add(blogDto);
		}
		if (blogDtolist.isEmpty()) {
			throw new BlogException("No Blogs associated with this Author");
		}
		return blogDtolist;
	}

	public List<BlogDto> fetchDotNetBlog() throws BlogException {
		List<BlogDto> blogDtolist = new ArrayList<>();
		List<Blog> blogs = blogRepository.findAll();
		for (Blog blog : blogs) {
			blogDto = modelMapper.map(blog, BlogDto.class);
			if (blogDto.getType().equalsIgnoreCase("Dotnet"))
				blogDtolist.add(blogDto);
		}
		if (blogDtolist.isEmpty()) {
			throw new BlogException("No Blogs associated with this Author");
		}
		return blogDtolist;
	}

	public List<BlogDto> fetchCategoryAuthorBlog(String Author, String type) throws BlogException {
		List<BlogDto> blogDtoList = new ArrayList<>();
		List<Blog> blogs = blogRepository.findAll();
		for (Blog blog : blogs) {
			blogDto = modelMapper.map(blog, BlogDto.class);
			if (blogDto.getUser().getUsername().equalsIgnoreCase(Author)) {
				if (type.equals(null))
					blogDtoList.add(blogDto);
				else if (blogDto.getType().equalsIgnoreCase(type))
					blogDtoList.add(blogDto);
			}
		}
		if (blogDtoList.isEmpty()) {
			throw new BlogException("No Blogs associated with this Author");
		}
		return blogDtoList;
	}

	public List<BlogDto> fetchAuthorBlog(String Author) throws BlogException {

		List<BlogDto> blogDtoList = new ArrayList<>();
		List<Blog> blogs = blogRepository.findAll();

		for (Blog blog : blogs) {
			blogDto = modelMapper.map(blog, BlogDto.class);
			if (blogDto.getUser().getUsername().equalsIgnoreCase(Author)) {
				blogDtoList.add(blogDto);
			}
		}
		if (blogDtoList.isEmpty()) {
			throw new BlogException("No Blogs associated with this Author");
		}
		return blogDtoList;
	}

	/*
	 * private List<BlogDto> blogAssembler(List<Blog> blogs){ List<BlogDto> blogList
	 * = new ArrayList<>(); blogs.forEach(blog -> { BlogDto blogDto = new BlogDto();
	 * blogDto.setBlog(blog.getBlog()); blogDto.setBlogDate(blog.getBlogDate());
	 * blogDto.setBlogTitle(blog.getBlogTitle());
	 * blogDto.setBlogid(blog.getBlogid()); blogDto.setRating(blog.getRating());
	 * blogDto.setTcomment(blog.getTcomment());
	 * blogDto.setRateCount(blog.getRateCount()); blogDto.setType(blog.getType());
	 * blogDto.setComments(blog.getComments()); blogDto.setUser(blog.getUser());
	 * blogList.add(blogDto); }); return blogList; }
	 */

	public List<CommentsDto> fetchAllComments() {
		List<CommentsDto> commentsDtoList = new ArrayList<>();
		List<Comments> commentsList = commentsRepository.findAll();
		for (Comments comments : commentsList) {
			commentsDto = modelMapper.map(comments, CommentsDto.class);
			commentsDtoList.add(commentsDto);
		}
		return commentsDtoList;
	}

	/*
	 * private List<CommentsDto> commentAssembler(List<Comments> com){
	 * List<CommentsDto> commList = new ArrayList<>(); com.forEach(co -> {
	 * CommentsDto commentsDto = new CommentsDto();
	 * commentsDto.setCommentid(co.getCommentid());
	 * commentsDto.setBlog(co.getBlog()); commentsDto.setComment(co.getComment());
	 * commList.add(commentsDto); }); return commList; }
	 */

	@Override
	public boolean RegisterC(CommentsDto cdo) {
		boolean value = false;
		Comments cc = modelMapper.map(cdo, Comments.class);
		commentsRepository.save(cc);
		value = true;
		return value;
	}

	@Override
	public boolean DelB(BlogDto bdo) {
		boolean value = false;
		{
			Blog bb = modelMapper.map(bdo, Blog.class);
			blogRepository.delete(bb);
			value = true;
		}
		return value;
	}

	@Override
	public boolean DelC(CommentsDto cdo) {
		boolean value = false;
		{
			Comments cc = modelMapper.map(cdo, Comments.class);
			commentsRepository.delete(cc);
			value = true;
		}
		return value;
	}

	@Override
	public boolean RegisterB(BlogDto bdo) {
		boolean value = false;
		{
			Blog bb = modelMapper.map(bdo, Blog.class);
			blogRepository.save(bb);
			value = true;
		}
		return value;
	}

	@Override
	public List<Blog> findAllById(int blogid) {
		return blogRepository.findAllById(blogid);
	}

	@Override
	public void updateTComment(int blogid, int commentn) {
		bsi.updateTComment(blogid, commentn);

	}

	@Override
	public List<BlogDto> findByBlogid(int blogid) throws BlogException {
		List<BlogDto> blogDtoList = new ArrayList<>();
		Blog blog = new Blog();
		blog = blogRepository.findByBlogid(blogid);
		if (blog == null) {
			throw new BlogException("No Blog with this ID");
		}
		BlogDto blogDto = new BlogDto();
		blogDto = modelMapper.map(blog, BlogDto.class);
		blogDtoList.add(blogDto);
		if (blogDtoList.isEmpty()) {
			throw new BlogException("No Blog with this ID");
		}
		return blogDtoList;
	}

	public Blog findByblogid(int blogid) {
		return blogRepository.findByBlogid(blogid);
	}

	public List<BlogDto> fetchAllBlog() throws BlogException {
		List<BlogDto> blogDtolist = new ArrayList<>();
		List<Blog> blogs = blogRepository.findAll();
		for (Blog blog : blogs) {
			blogDto = modelMapper.map(blog, BlogDto.class);
			blogDtolist.add(blogDto);
		}
		if (blogDtolist.isEmpty()) {
			throw new BlogException("No Blogs Written");
		}
		return blogDtolist;
	}

	@Override
	public void updateBlog(int blogid, String blog) {
		bsi.updateBlog(blogid, blog);

	}

	@Override
	public int countBlogById(int userid) {
		return blogRepository.countBlogById(userid);
	}

	@Override
	public ModelAndView funcOpenBlog(ModelMap modelMap, @RequestParam int blogid) throws BlogException {
		List<BlogDto> blogDtoList = new ArrayList<>();
		List<BlogDto> blogDtoList1 = new ArrayList<>();
		ModelAndView modelAndView = new ModelAndView();
		int userid = 0;
		String emailn = null;
		List<CommentsDto> commentsDtoList = new ArrayList<>();
		blogDtoList = fetchAllBlog();

		Blog blog = findByblogid(blogid);

		if (blog.getBlogid() == blogid) {

			userid = blog.getUser().getUserid();
			emailn = blog.getUser().getEmail();
		}
		commentsDtoList = fetchAllComments();

		int totalBlogs = countBlogById(userid);

		modelMap.put("totalBlogs", totalBlogs);
		modelMap.put("blogDtoList", blogDtoList);
		modelMap.put("commentsDtoList", commentsDtoList);
		modelMap.put("blogidd", blogid);
		modelMap.put("emailn", emailn);
		modelAndView.setViewName("OpenBlog");
		logger.info("Opened Individual Blog");
		return modelAndView;
	}

	@Override
	public ModelAndView funcShowRatedCommentedBlog(ModelMap modelMap, int blogid, HttpSession session)
			throws BlogException {
		ModelAndView modelAndView = new ModelAndView();
		List<BlogDto> blogDtoList = new ArrayList<>();
		int uid = 0;
		String emailn = null;
		List<CommentsDto> commentsDtoList = new ArrayList<>();
		blogDtoList = fetchAllBlog();
		Blog blog = findByblogid(blogid);
		if (blog.getBlogid() == blogid) {
			uid = blog.getUser().getUserid();
			emailn = blog.getUser().getEmail();
		}
		commentsDtoList = fetchAllComments();
		int totalBlogs = countBlogById(uid);
		modelMap.put("totalBlogs", totalBlogs);
		modelMap.put("blogDtoList", blogDtoList);
		modelMap.put("commentsDtoList", commentsDtoList);
		modelMap.put("blogidd", blogid);
		modelMap.put("emailn", emailn);
		modelMap.put("rate", "done");
		modelAndView.setViewName("OpenBlog");

		logger.info("Showing particular Blog with the added Rate and comment");
		return modelAndView;
	}

	@Override
	public ModelAndView funcupdateRateComment(BlogDto blogDto, CommentsDto commentsDto, float rate) {
		String rEmail = commentsDto.getrEmail();
		String rName = commentsDto.getrName();
		String comment = commentsDto.getComment();
		int tcomment = blogDto.getTcomment();
		int blogid = blogDto.getBlogid();
		float rating = blogDto.getRating();
		int rateCount = blogDto.getRateCount();
		tcomment += 1;
		rateCount = rateCount + 1;
		Blog blog = findByblogid(blogid);
		BlogDto blogDto1 = new BlogDto();
		blogDto1 = modelMapper.map(blog, BlogDto.class);
		blogDto1.setTcomment(tcomment);
		if (rating != 0)
			rate = ((rating * (rateCount - 1)) + rate) / rateCount;
		blogDto1.setRating(rate);
		blogDto1.setRateCount(rateCount);
		CommentsDto commentsDto1 = new CommentsDto();
		commentsDto1.setrName(rName);
		commentsDto1.setrEmail(rEmail);

		commentsDto1.setBlog(blogDto1);
		commentsDto1.setComment(comment);
		RegisterC(commentsDto1);
		RegisterB(blogDto1);
		ModelMap modelMap = new ModelMap();
		modelMap.put("blogid", blogid);
		logger.info("Rating and Comment added succesfully");
		return new ModelAndView("redirect:/Blog/open/submit/rateComment/" + blogid);
	}

	@Override
	public ModelAndView funcUpdateBlog(BlogDto blogDto) throws BlogException {
		int blogid = blogDto.getBlogid();
		Blog blog1 = blogRepository.findByBlogid(blogid);
		BlogDto blogDto1 = new BlogDto();
		blogDto1 = modelMapper.map(blog1, BlogDto.class);
		blogDto1.setBlogTitle(blogDto.getBlogTitle());
		blogDto1.setBlog(blogDto.getBlog());
		if (RegisterB(blogDto1)) {
			logger.info("Editing New added Blog");
			return new ModelAndView("redirect:/Dashboard/edit/show");
		} else {
			throw new BlogException("Sorry, Unable to performing Editing");
		}
	}

	@Override
	public ModelAndView funcSubmitBlog(int userid, BlogDto blogDto) throws BlogException {
		List<User> userDtoList;
		User u = new User();
		userDtoList = userService.findAllById(userid);
		for (User user : userDtoList) {
			u = user;
		}
		UserDto userDto = modelMapper.map(u, UserDto.class);
		blogDto.setUser(userDto);
		blogDto.setBlogDate(new java.util.Date());
		logger.info("New Blog Added");
		if (RegisterB(blogDto))
			return new ModelAndView("redirect:/Dashboard/add/show");
		else {
			logger.info("New Blog Not added");
			throw new BlogException("New Blog Not added");
		}
	}

	@Override
	public ModelAndView funcDeleteBlog(int blogid) {
		Blog blog = blogRepository.findByBlogid(blogid);
		BlogDto blogDto = modelMapper.map(blog, BlogDto.class);
		List<CommentsDto> commentsDtoList = fetchAllComments();
		for (CommentsDto commentsDto : commentsDtoList) {
			if (commentsDto.getBlog().getBlogid() == blogid) {
				DelC(commentsDto);
			}
		}
		DelB(blogDto);
		logger.info("Deleted the Blog form BlogList");
		return new ModelAndView("redirect:/Dashboard/view/deleted");

	}

	@Override
	public ModelAndView funcEditingBlog(ModelMap modelMap, BlogDto blogDto) throws BlogException {
		int blogid = blogDto.getBlogid();
		String blogTitle = blogDto.getBlogTitle();
		String blog = blogDto.getBlog();
		BlogDto blogDto1 = new BlogDto();
		Blog blog1 = new Blog();
		List<Blog> blogDtoList = new ArrayList<>();
		blogDtoList = findAllById(blogid);
		for (Blog blog2 : blogDtoList) {
			blog1 = blog2;
		}
		ModelMapper modelMapper = new ModelMapper();
		blogDto1 = modelMapper.map(blog1, BlogDto.class);
		blogDto1.setBlogTitle(blogTitle);
		blogDto1.setBlog(blog);
		if (RegisterB(blogDto1)) {
			logger.info("Showing the edited Blog form BlogList");
			return new ModelAndView("redirect:/Dashboard/view/edited");
		} else
			throw new BlogException("Cannot Edit Blog");

	}

	@Override
	public Blog createBlog(BlogDto blogDto) {
		blogDto.setBlogDate(new java.util.Date());
		Blog blog = modelMapper.map(blogDto, Blog.class);
		User b=userService.findByUserid(blog.getUser().getUserid());
		blog.setUser(b);
		return blogRepository.save(blog);
	}

	@Override
	public Blog updatedBlog(BlogDto blogDto) throws BlogException {
		int idcheck = blogDto.getBlogid();
		if (blogRepository.findByBlogid(idcheck) != null) {
			Blog blog = blogRepository.findByBlogid(idcheck);
			blog.setBlog(blogDto.getBlog());
			blog.setBlogTitle(blogDto.getBlogTitle());
			blog.setType(blogDto.getType());
			return blogRepository.save(blog);
		} else {
			return null;
		}
	}

	@Override
	public boolean deletedBlog(BlogDto blogDto) {
		int idcheck = blogDto.getBlogid();
		if (blogRepository.findByBlogid(idcheck) != null) {
			Blog blog = blogRepository.findByBlogid(idcheck);
			blogRepository.delete(blog);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean deletedBlogUsingId(int blogid) {
		if (blogRepository.findByBlogid(blogid) != null) {
			Blog blog = blogRepository.findByBlogid(blogid);
			blogRepository.delete(blog);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String rateCommentedBlog(BlogDto blogDto) {
		CommentsDto commentsDto = new CommentsDto();
		for (CommentsDto comm : blogDto.getComments()) {
			commentsDto = comm;
		}
		Comments com = new Comments();
		com = modelMapper.map(commentsDto, Comments.class);
		int blogid = blogDto.getBlogid();
		float rating = blogDto.getRating();
		if (blogRepository.findByBlogid(blogid) != null) {
			Blog blog = blogRepository.findByBlogid(blogid);
			int tcomment = blog.getTcomment();
			float rate = blog.getRating();
			tcomment += 1;
			int rateCount = blog.getRateCount();
			rateCount = rateCount + 1;
			if (rate != 0)
				rating = ((rate * (rateCount - 1)) + rating) / rateCount;
			blog.setTcomment(tcomment);
			blog.setRateCount(rateCount);
			blog.setRating(rating);
			com.setBlog(blog);
			commentsRepository.save(com);
			 blogRepository.save(blog);
			 return "Your Rating: "+blogDto.getRating()+" & Comment: '"+com.getComment()+"' is saved";
		} else {
			return null;
		}
	}

}
