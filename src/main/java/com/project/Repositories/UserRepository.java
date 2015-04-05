package com.project.Repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.Entities.UserMstr;

public interface UserRepository extends JpaRepository<UserMstr, Serializable>{

	@Query("select u from UserMstr u where u.email=?1 and u.password=?2")
	UserMstr login(String email, String password);

	UserMstr findByEmailAndPassword(String email, String password);

	UserMstr findUserByEmail(String email);

}