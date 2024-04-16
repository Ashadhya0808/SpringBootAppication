package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserDAOService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1,"Sharanya", LocalDate.now().minusYears(30), "Sharanya"));
        users.add(new User(2,"Ashish", LocalDate.now().minusYears(34), "Ashish"));
        users.add(new User(3,"Aadhya", LocalDate.now().minusYears(3), "Aadhya"));
    }

    public List<User> findAllUsers(){
        return users;
    }

    public User saveUser(User user){
        users.add(user);
        return user;
    }

    public User findOneUser(Integer id){
        return users.stream().filter(user -> Objects.equals(user.getId(), id)).findFirst().orElse(null);
    }

    public User deleteUserById(Integer id){
        User userToBeDeleted = users.stream().filter(user -> Objects.equals(user.getId(), id)).findFirst().orElse(null);
        if(Objects.nonNull(userToBeDeleted)) {
            users.remove(userToBeDeleted);
        }
        return userToBeDeleted;
    }
}
