package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserDAOService;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-internalization")
    public String helloWorldInternalization(){
        Locale locale = LocaleContextHolder.getLocale(); //gets locale from the existing context - request
        return messageSource.getMessage("good.morning.message",null,"Default Message",locale);
    }

    @GetMapping(path = "/hello-world-demo")
    public HelloWorldDemo helloWorldDemo(){
        return new HelloWorldDemo("Hello World");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldDemo hello(@PathVariable String name){
        return new HelloWorldDemo("Hello World, " + name);// new HelloWorldDemo(String.format("Hello World, %s" , name))
    }

}
