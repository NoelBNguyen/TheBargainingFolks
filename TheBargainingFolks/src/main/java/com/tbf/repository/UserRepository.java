package com.tbf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tbf.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	
	@Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
	public User checkValidLogin(@Param("email") String email, @Param("password") String pass);
}
