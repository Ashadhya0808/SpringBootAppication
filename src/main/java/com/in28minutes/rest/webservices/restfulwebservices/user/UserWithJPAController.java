package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.repository.UserSpringDataJPARepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
public class UserWithJPAController {

    @Autowired
    private UserSpringDataJPARepository jpaRepository;

    @GetMapping(path = "/jpa/users")
    public List<User> getUsers(){
        return jpaRepository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> getUsersById(@PathVariable Integer id){
        User user = jpaRepository.findById(id).orElse(null);
        assert user != null;
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public User deleteUsersById(@PathVariable Integer id){
        User user = jpaRepository.findById(id).orElse(null);
        assert user != null;
        jpaRepository.deleteById(id);
        return user;
    }

    /*@PostMapping("/testPost")
    public User cUser(){
        return new User(1,"Sha", LocalDate.now(),"Sha");
    }*/

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User createdUser = jpaRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
