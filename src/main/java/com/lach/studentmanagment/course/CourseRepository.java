package com.lach.studentmanagment.course;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {

    private final Map<UUID, Course> idToCourse;

    public CourseRepository(Map<UUID, Course> idToCourse) {
        this.idToCourse = idToCourse;
    }

    public Course save(Course course){
        idToCourse.put(course.getCourseId(), course);
        return idToCourse.get(course.getCourseId());
    }

    // update - replace instance of object Course
    public Course update(Course course){
        idToCourse.replace(course.getCourseId(), course);
        return idToCourse.get(course.getCourseId());
    }

    //delete - remove instance of object Course of CourseID number
    public void delete(UUID courseId){
        idToCourse.remove(courseId);
    }

    // findById - it will be search an instance of object Course of CourseID number
    public Course find(UUID courseId){
        return idToCourse.get(courseId);
    }

    //findAll - returns list of all Course with that same name
    public List<Course> findAll(String courseName){
//        List<Course> courseList = new LinkedList<>();
//        for (Course course : idToCourse.values()) {
//            if (course.getCourseName().equals(courseName)) courseList.add(course);
//        }
//        return courseList;
        return idToCourse.values().stream()
                .filter((course) -> course.getCourseName().equals(courseName))
                .collect(Collectors.toList());
    }

    //Overload findAll - returns list of all course in repository
    public List<Course> findAll(){
//        return new ArrayList<>(idToCourse.values());
        return idToCourse.values().stream().toList();
    }
}
