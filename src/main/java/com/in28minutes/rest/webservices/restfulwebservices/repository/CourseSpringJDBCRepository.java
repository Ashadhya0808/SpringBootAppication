package com.in28minutes.rest.webservices.restfulwebservices.repository;

import com.in28minutes.rest.webservices.restfulwebservices.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseSpringJDBCRepository {

    private final String SELECT_QUERY_FOR_COURSE;
    private final String DELETE_COURSE_QUERY;
    private final String INSERT_COURSE_QUERY;

    private final String SELECT_QUERY;
    /*private final String INSERT_USER_QUERY;*/

    {
        /*INSERT_USER_QUERY = """
                    insert into user (id,name,birthDate)
                    values (1, 'Learn JPA', 09-09-1995)
                """;*/
        INSERT_COURSE_QUERY = """
                    insert into course (id,name,author)
                    values (?,?,?)
                """;
        DELETE_COURSE_QUERY = """
                delete from course where id=?
                """;
        SELECT_QUERY_FOR_COURSE = """
            SELECT * FROM COURSE where id = ?
            """;
        SELECT_QUERY = """
                SELECT * from course
                """;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*public void insertUserData(){
        jdbcTemplate.update(INSERT_USER_QUERY);
    }*/

    public void insertCourseData(Course course) {
        jdbcTemplate.update(INSERT_COURSE_QUERY,course.getId(),course.getNameOfCourse(),course.getNameOfAuthor());
    }

    public void deleteCourseById(Integer id){
        jdbcTemplate.update(DELETE_COURSE_QUERY,id);
    }

    public Course getCourseById(int id) {
        BeanPropertyRowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
        return jdbcTemplate.queryForObject(SELECT_QUERY_FOR_COURSE,rowMapper,id);
    }

    public List<Course> getCoursesList(){
        BeanPropertyRowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
        return jdbcTemplate.query(SELECT_QUERY, rowMapper);
    }
}
