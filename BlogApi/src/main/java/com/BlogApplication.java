	package com;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dto.BlogDto;
import com.dto.CommentsDto;
import com.dto.UserDto;
import com.model.Blog;
import com.model.Comments;
import com.model.User;


@SpringBootApplication
//@ComponentScan(basePackages= {"com.restController","com.controller","com.service","com.exception"})
//@EnableJpaRepositories("com.repository")
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	
	
	
}
