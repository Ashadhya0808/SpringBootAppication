package com.in28minutes.rest.webservices.restfulwebservices.repository;

import com.in28minutes.rest.webservices.restfulwebservices.posts.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostSpringDataJPARepository extends JpaRepository<Post, Integer> {
}
