package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Blog;
import com.model.Comments;
import com.model.User;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer>{

	
	/*@Query("SELECT r FROM Comments r where r.blog.blogid = :blogid") 
    List<Comments> findCommentsById(@Param("blogid") int blogid);*/
}
