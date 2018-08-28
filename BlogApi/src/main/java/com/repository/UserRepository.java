package com.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Blog;
import com.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsernameAndPassword(String username,String password);

	public User findByEmailAndPassword(String email, String password);
	
	public User findByEmail(String email);
	
	public User findByUserid(int userid); 
	
	@Query("SELECT r FROM User r where r.userid = :userid") 
 public   List<User> findBlogByUserId(@Param("userid") int userid);
	
	@Query("SELECT r.userid FROM User r where r.email = :email") 
	    int findIdByName(@Param("email") String email);
	   
	
	@Query("SELECT r FROM User r where r.userid = :userid") 
    List<User> findAllById(@Param("userid") int userid);
	
	
}
