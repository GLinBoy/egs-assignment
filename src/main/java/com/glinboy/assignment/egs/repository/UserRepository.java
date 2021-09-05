package com.glinboy.assignment.egs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.glinboy.assignment.egs.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
	Boolean existsByEmail(String email);
	
	@Modifying
	@Query("UPDATE User u SET u.blocked = ?2 WHERE u.id = ?1")
	void updateUserBlockStatus(Long id, Boolean status);

}
