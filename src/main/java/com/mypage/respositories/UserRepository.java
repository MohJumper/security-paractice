package com.mypage.respositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mypage.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	// this is a custom query which will create sm like this
		// select * from user where username = :username
//	@Query("select u from User u"
//			+ "left join fetch u.authorities"
//			+ "where u.username = :username")
//	@Transactional
	User findByUsername(String username);
	
}
