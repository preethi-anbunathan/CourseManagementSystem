package com.example.webdevsummer12018.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.webdevsummer12018.models.User;

@Repository

public interface UserRepository extends CrudRepository<User, Integer> {
	@Query("SELECT u FROM User u WHERE u.username =:username AND u.password=:password")
	Iterable<User> findUserByCredentials(@Param("username") String username, @Param("password") String password);

	@Query("SELECT u FROM User u WHERE u.username =:username")
	Iterable<User> findUserByUsername(@Param("username") String username);
}