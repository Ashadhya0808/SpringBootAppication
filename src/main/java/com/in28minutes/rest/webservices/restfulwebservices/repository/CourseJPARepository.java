package com.in28minutes.rest.webservices.restfulwebservices.repository;

import com.in28minutes.rest.webservices.restfulwebservices.course.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CourseJPARepository {

    private String Select_Query = "SELECT c from Course c";

    @PersistenceContext
    private EntityManager entityManager;

    public void insertCourseData(Course course) {
        entityManager.merge(course);
    }

    public Course getCourseById(int id) {
        return entityManager.find(Course.class,id);
    }

    public void deleteCourseById(Integer id){
        Course course = getCourseById(id);
        entityManager.remove(course);
    }

    public List<Course> getCoursesList(){
        Query query = entityManager.createQuery(Select_Query);
        return query.getResultList();
    }
}
