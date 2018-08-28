package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dto.BlogDto;
import com.model.Blog;
import com.model.User;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
 
	/*public Blog findOne(int blogid);*/
	@Query("SELECT r FROM Blog r where r.blogid = :blogid") 
    List<Blog> findAllById(@Param("blogid") int blogid);
	
	public Blog findByBlogid(int blogid); 
	
	@Query("SELECT count(r.blogid) FROM Blog r where r.user.userid = :userid") 
    int countBlogById(@Param("userid") int userid);
	
}
