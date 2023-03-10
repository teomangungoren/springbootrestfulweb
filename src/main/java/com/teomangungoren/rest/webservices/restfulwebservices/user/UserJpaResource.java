package com.teomangungoren.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.teomangungoren.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
public class UserJpaResource {

	
	@Autowired
	private UserDaoService service;
	
	private UserRepository repository;
	
	public UserJpaResource(UserDaoService service,UserRepository repository) {
		this.service=service;
		this.repository=repository;
	}
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return repository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id){
	Optional<User> user= repository.findById(id);
	
	if(user.isEmpty()) {
		throw new UserNotFoundException("id:"+id);
	}
	EntityModel<User> entityModel=EntityModel.of(user.get());
	
	WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAllUsers());
	entityModel.add(link.withRel("all-users"));
	
	return entityModel;
	
	}
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		repository.deleteById(id);
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
	User savedUser=repository.save(user);	
	URI location=ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
	return ResponseEntity.created(location).build();
	}
}
