package com.in28minutes.rest.webservices.restfulwebservices.course;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

@Entity
public class Course {

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "name")
    private String nameOfCourse;
    @Column(name = "author")
    private String nameOfAuthor;

    public Course() {
    }

    public Course(int id, String nameOfCourse, String nameOfAuthor) {
        super();
        this.id = id;
        this.nameOfCourse = nameOfCourse;
        this.nameOfAuthor = nameOfAuthor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    public void setNameOfCourse(String nameOfCourse) {
        this.nameOfCourse = nameOfCourse;
    }

    public String getNameOfAuthor() {
        return nameOfAuthor;
    }

    public void setNameOfAuthor(String nameOfAuthor) {
        this.nameOfAuthor = nameOfAuthor;
    }

    @Override
    public String toString() {
        return "Course{" +
               "id=" + id +
               ", nameOfCourse='" + nameOfCourse + '\'' +
               ", nameOfAuthor='" + nameOfAuthor + '\'' +
               '}';
    }
}
