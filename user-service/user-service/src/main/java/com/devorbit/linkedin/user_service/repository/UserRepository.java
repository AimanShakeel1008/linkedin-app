package com.devorbit.linkedin.user_service.repository;

import com.devorbit.linkedin.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
	Optional<User> findByEmail(String email);

	boolean existsByEmail(java.lang.String email);
}
