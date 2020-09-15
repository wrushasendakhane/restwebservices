package com.in28minutes.restwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.in28minutes.restwebservices.exception.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/jpa")
public class UserJpaController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/users")
  public List<User> retrieveAllUsers() {
    return userRepository.findAll();
  }

  @PostMapping("/users")
  public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
    User savedUser = userRepository.save(user);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
        .toUri();
    return ResponseEntity.created(location).build();
  }

  @GetMapping("/users/{id}")
  public EntityModel<User> retrieveUser(@PathVariable("id") int id) {
    Optional<User> user = userRepository.findById(id);
    if (!user.isPresent()) {
      throw new UserNotFoundException(String.format("User Id= %s Not Found", id));
    }

    // HATEOAS Implementation
    EntityModel<User> resource = EntityModel.of(user.get());
    WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
    resource.add(linkTo.withRel("all-users"));

    return resource;
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable("id") int id) {
    userRepository.deleteById(id);
  }
}
