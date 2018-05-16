package com.example.webdevsummer12018.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer12018.repositories.UserRepository;

import com.example.webdevsummer12018.models.User;

@RestController
public class UserService {
	@Autowired
	UserRepository repository;
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) repository.findAll();
	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setPassword(newUser.getPassword());
			user.setEmail(newUser.getEmail());
			user.setDob(newUser.getDob());
			user.setPhone(newUser.getPhone());
			repository.save(user);
			return user;
		}
		return null;
	}
	
	
	@PostMapping("/api/login")

	public User login(@RequestBody User user, HttpSession session, HttpServletResponse response) {

		User fetchedUser = null;
		Iterable<User> result = repository.findUserByCredentials(user.getUsername(), user.getPassword());
		for (User userval : result) {
			fetchedUser = userval;
			break;
		}
		if (fetchedUser != null) {
			user.setId(fetchedUser.getId());
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(1800);
		} else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
		return fetchedUser;
	}

	@GetMapping("/api/user/username/{username}")
	public User findUserByUsername(String username) {
		User user = null;
		Iterable<User> result = repository.findUserByUsername(username);
		for (User userval : result) {
			user = userval;
			break;

		}
		return user;
	}
	
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session, HttpServletResponse response) {
		User data = findUserByUsername(user.getUsername());
		if (data == null) {
			repository.save(user);
			user = findUserByUsername(user.getUsername());
			session.setAttribute("user", user);
		} else {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		}
		return user;
	}
}
