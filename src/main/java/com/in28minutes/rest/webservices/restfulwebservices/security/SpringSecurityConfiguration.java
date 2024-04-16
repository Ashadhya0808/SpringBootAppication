package com.in28minutes.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        //adds security for all http pages
        security.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        //displays authentication popup if not authenticated
        security.httpBasic(withDefaults());
        //CSRF -> for POST and PUT
        security.csrf(AbstractHttpConfigurer::disable);
        return security.build();
    }
}
