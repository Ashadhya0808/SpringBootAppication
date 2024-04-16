package com.in28minutes.rest.webservices.restfulwebservices.course;

import com.in28minutes.rest.webservices.restfulwebservices.course.Course;
import com.in28minutes.rest.webservices.restfulwebservices.repository.CourseJPARepository;
import com.in28minutes.rest.webservices.restfulwebservices.repository.CourseSpringDataJPARepository;
import com.in28minutes.rest.webservices.restfulwebservices.repository.CourseSpringJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

    /*  For Spring JDBC
    @Autowired
    private CourseSpringJDBCRepository repo;*/

    /*  For Spring Data Jpa
    @Autowired
    private CourseJPARepository jpaRepository;*/

    @Autowired
    private CourseSpringDataJPARepository jpaRepository;

    @Override
    public void run(String... args) throws Exception {
        /* For Spring JDBC
        repo.insertUserData();
        repo.insertCourseData(new Course(1,"AWS","Stephan"));
        repo.insertCourseData(new Course(2,"Spring Boot","Ranga"));
        repo.insertCourseData(new Course(3,"AWS Developer","Stephane"));
        repo.deleteCourseById(1);
         System.out.println("Data for ID: 2 - "+ repo.getCourseById(2));
        System.out.println("List Data for Course : ");
        for(Course course : repo.getCoursesList()){
            System.out.println(course);
        }*/

        /* For JPA
        jpaRepository.insertCourseData(new Course(1,"AWS","Stephan"));
        jpaRepository.insertCourseData(new Course(2,"Spring Boot","Ranga"));
        jpaRepository.insertCourseData(new Course(3,"AWS Developer","Stephane"));
        jpaRepository.deleteCourseById(1);
        Course course = jpaRepository.getCourseById(2);
        System.out.println("Course for ID:2 - "+course);
        System.out.println("List Data for Course : ");
        for(Course jpaCourse : jpaRepository.getCoursesList()){
            System.out.println(jpaCourse);
        }*/

        /*Spring JPA
        jpaRepository.save(new Course(1,"AWS","Stephan"));
        jpaRepository.save(new Course(2,"Spring Boot","Ranga"));
        jpaRepository.save(new Course(3,"AWS Developer","Stephane"));

        jpaRepository.deleteById(1);
        Course course = jpaRepository.findById(2).orElse(null);
        System.out.println("Course for ID:2 - "+course);
        System.out.println("List Data for Course : ");
        System.out.println(jpaRepository.findAll());

        System.out.println("Finding By Author: " + jpaRepository.findByNameOfAuthor("Ranga"));
        System.out.println("Finding By Name: " + jpaRepository.findByNameOfCourse("AWS Developer"));
        */

    }
}
