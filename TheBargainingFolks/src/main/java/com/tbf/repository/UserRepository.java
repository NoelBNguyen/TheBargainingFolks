package com.tbf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tbf.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
