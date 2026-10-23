package com.example.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.pojo.entity.User;

public interface UserRepository  extends JpaRepository<User, String> {
	@Query("SELECT MAX(u.id) FROM User u")
    String findMaxId();
	
	Optional<User> findByLoginId(String loginId);
	boolean existsByName(String name);
}
