package com.in28minutes.rest.webservices.restfulwebservices.posts;

import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.repository.PostSpringDataJPARepository;
import com.in28minutes.rest.webservices.restfulwebservices.repository.UserSpringDataJPARepository;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PostJPAController {

    private PostSpringDataJPARepository jpaRepository;
    private UserSpringDataJPARepository userJpaRepository;

    public PostJPAController(PostSpringDataJPARepository jpaRepository, UserSpringDataJPARepository userJpaRepository) {
        this.jpaRepository = jpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @GetMapping(value = "/posts")
    public List<Post> getAllPosts(){
        return jpaRepository.findAll();
    }

    @GetMapping(value = "/jpa/users/{id}/posts")
    public List<Post> getAllPostsForUserById(@PathVariable  Integer id){
        Optional<User> user = userJpaRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User Is Not Found");
        }
        return user.get().getPosts();

    }

    @PostMapping(value = "/jpa/users/{id}/posts")
    public ResponseEntity<Post> createPostToUser(@PathVariable Integer id, @RequestBody Post post){
        Optional<User> user = userJpaRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User Is Not Found");
        }
        post.setUser(user.get());
        Post savedPost = jpaRepository.save(post);
        /* No need to save into user
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        user.get().setPosts(posts);userJpaRepository.save(user.get());*/
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/jpa/users/{userID}/posts/{id}")
    public Post getPostById(@PathVariable Integer id, @PathVariable String userID){
        Post post = jpaRepository.findById(id).orElse(null);
        if(post == null){
            throw new UserNotFoundException("post not found");
        }
        return post;
    }



}
