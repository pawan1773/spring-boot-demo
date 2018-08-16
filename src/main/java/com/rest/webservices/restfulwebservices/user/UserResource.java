package com.rest.webservices.restfulwebservices.user;

import java.net.URI;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.rest.webservices.restfulwebservices.exception.NoSuchUserException;

@RestController
@RequestMapping(path = "resource/")
public class UserResource {

	private UserDaoService userDaoService;

	UserResource() {

	}

	@Autowired
	UserResource(UserDaoService userDaoService) {
		super();
		this.userDaoService = userDaoService;
	}

	@GetMapping(path = "users")
	public List<User> retrieveAllUsers() {
		return userDaoService.findAll();
	}

	@GetMapping(path = "users/{id}")
	public Resource<User> retrieveUser(@PathVariable("id") int id) {
		User user = userDaoService.findOne(id);
		if (null == user)
			throw new NoSuchUserException("No user with id: " + id);

		// HATEOAS: Hypermedia As The Engine Of Application State
		Resource<User> resource = new Resource<User>(user);
		// linkTo is a static method of ControllerLinkBuilder
		// we have used static import to make it work
		// we will send link to access all users in response all with actual response
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		// return user;
		return resource;
	}

	@PostMapping(path = "users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "users/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		User user = userDaoService.deleteById(id);
		if (null == user)
			throw new NoSuchUserException("No user with id: " + id);

	}
}
