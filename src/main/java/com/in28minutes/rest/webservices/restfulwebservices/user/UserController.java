package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @GetMapping(path = "/users")
    public List<User> getUsers(){
        return new UserDAOService().findAllUsers();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> getUsersById(@PathVariable Integer id){
        User oneUser = new UserDAOService().findOneUser(id);
        if(oneUser == null){
            throw new UserNotFoundException("id: " + id);
        }

        EntityModel<User> model = EntityModel.of(oneUser); //to respond with any links
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsers());
        model.add(link.withRel("all-users"));

        return model;
    }

    @DeleteMapping(path = "/users/{id}")
    public User deleteUsersById(@PathVariable Integer id){
        User oneUser = new UserDAOService().deleteUserById(id);

        if(oneUser == null){
            throw new UserNotFoundException("id: " + id);
        }

        return oneUser;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User createdUser = new UserDAOService().saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(location).build(); // to change the response code from 200 to 201
    }
}
