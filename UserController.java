package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Controller
@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
	@Autowired
//	private UserRepository userepository;
	private CrudRepository<User, Long> UserRepository;
	
	@PostMapping("/user")
	User newuser(@RequestBody User newuser) {
		
			return UserRepository.save(newuser);
	}
	
	@GetMapping("/users")
		List<User> getAllusers(){
			return (List<User>) UserRepository.findAll();
			
		}
	
	@GetMapping("/user/{id}")
	User getUserById(@PathVariable Long id) {
		return UserRepository.findById(id).orElseThrow( ()->new UserNotFoundException(id));
		
	}
	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User newuser,@PathVariable Long id) {
		return UserRepository.findById(id).map( User->{
			User.setName(newuser.getName());
			User.setEmail(newuser.getEmail());
			return UserRepository.save(User);
		}).orElseThrow( ()->new UserNotFoundException(id));
	}
	
	@DeleteMapping("/user/{id}")
	String deleteuser(@PathVariable Long id) {
		if(!UserRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		UserRepository.deleteById(id);
		return "user with id"+id+"has been deleted";
	}
}
