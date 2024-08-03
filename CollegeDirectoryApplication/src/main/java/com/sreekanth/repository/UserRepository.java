package com.sreekanth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sreekanth.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);

	User findByEmail(String email);

}
