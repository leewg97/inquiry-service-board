package com.fastcampus.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fastcampus.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
// DAO 클래스랑 같은 기능이라고 보면 됨. 인터페이스로 만들었다고 생각하기

	// SELECT * FROM user WHERE username = ?1;
	Optional<User> findByUsername(String username);

	
}
