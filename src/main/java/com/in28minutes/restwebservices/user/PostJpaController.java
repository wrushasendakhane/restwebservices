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
@RequestMapping("/users")
public class PostJpaController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PostRepository postRepository;

  @GetMapping("/{userId}/posts")
  public List<Post> retrieveAllPosts(@PathVariable int userId) {
    Optional<User> user = userRepository.findById(userId);
    if (!user.isPresent()) {
      throw new UserNotFoundException(String.format("User Id= %s Not Found", userId));
    }
    return user.get().getPosts();
  }

  @PostMapping("/{userId}/posts")
  public ResponseEntity<Object> addPost(@PathVariable int userId, @Valid @RequestBody Post post) {
    Optional<User> user = userRepository.findById(userId);
    if (!user.isPresent()) {
      throw new UserNotFoundException(String.format("User Id= %s Not Found", userId));
    }

    post.setUser(user.get());
    Post savedPost = postRepository.save(post);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
        .toUri();
    return ResponseEntity.created(location).build();
  }

  @GetMapping("/{userId}/posts/{id}")
  public EntityModel<Post> retrievePost(@PathVariable int userId, @PathVariable("id") int id) {
    Optional<User> user = userRepository.findById(userId);
    if (!user.isPresent()) {
      throw new UserNotFoundException(String.format("User Id= %s Not Found", userId));
    }
    Optional<Post> post = postRepository.findById(id);
    // HATEOAS Implementation
    EntityModel<Post> resource = EntityModel.of(post.get());
    WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllPosts(userId));
    resource.add(linkTo.withRel("all-posts"));

    return resource;
  }

  @DeleteMapping("/{userId}/posts/{id}")
  public void deletePost(@PathVariable int userId, @PathVariable("id") int id) {
    Optional<User> user = userRepository.findById(userId);
    if (!user.isPresent()) {
      throw new UserNotFoundException(String.format("User Id= %s Not Found", userId));
    }
    postRepository.deleteById(id);
  }
}
