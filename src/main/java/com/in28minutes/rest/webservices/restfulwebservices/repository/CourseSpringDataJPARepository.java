package com.in28minutes.rest.webservices.restfulwebservices.repository;

import com.in28minutes.rest.webservices.restfulwebservices.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseSpringDataJPARepository extends JpaRepository<Course , Integer> {
    List<Course> findByNameOfAuthor(String nameOfAuthor);
    List<Course> findByNameOfCourse(String nameOfCourse);
}
