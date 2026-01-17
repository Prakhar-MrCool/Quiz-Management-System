package com.quiz.Quiz_Management_System.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.Quiz_Management_System.Entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUserName(String username);

}
