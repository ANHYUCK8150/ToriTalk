package com.toritalk.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toritalk.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Boolean existsByName(String name);
}
