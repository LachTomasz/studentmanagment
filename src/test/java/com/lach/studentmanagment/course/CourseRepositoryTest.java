package com.lach.studentmanagment.course;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseRepositoryTest {

    @Test
    void shouldSaveCourse() {
        //Given
        CourseRepository courseRepository = new CourseRepository(new HashMap<>());
        Course course = new Course("Math");

        //When
        Course result = courseRepository.save(course);

        //Then
        Assertions.assertEquals(course, result);
    }

    @Test
    void shouldUpdateCourse() {
        //Given
        Course course1 = new Course("Math");
        Course course2 = new Course("Physics");
        Map<UUID, Course> courseMap = new HashMap<>();
        courseMap.put(course1.getCourseId(), course1);
        courseMap.put(course2.getCourseId(), course2);
        CourseRepository courseRepository = new CourseRepository(courseMap);
        Course updateCourse = new Course(course1.getCourseId(), "Algebra");

        //When
        Course result = courseRepository.update(updateCourse);

        //Then
        Assertions.assertEquals(updateCourse, result);
    }

    @Test
    void shouldDeleteCourse() {
        //Given
        Course course1 = new Course("Math");
        Course course2 = new Course("Physics");
        Map<UUID, Course> courseMap = new HashMap<>();
        courseMap.put(course1.getCourseId(), course1);
        courseMap.put(course2.getCourseId(), course2);
        CourseRepository courseRepository = new CourseRepository(courseMap);

        //When
        courseRepository.delete(course1.getCourseId());

        //Then
        Assertions.assertNull(courseRepository.find(course1.getCourseId()));
    }

    @Test
    void shouldFindCourse() {
        //Given
        Course course1 = new Course("Math");
        Course course2 = new Course("Physics");
        Map<UUID, Course> courseMap = new HashMap<>();
        courseMap.put(course1.getCourseId(), course1);
        courseMap.put(course2.getCourseId(), course2);
//        Map<UUID, Course> courseMap = Map.of(course1.getCourseId(),course1,
//                course2.getCourseId(), course2);
        CourseRepository courseRepository = new CourseRepository(courseMap);

        //When
        Course result = courseRepository.find(course1.getCourseId());

        //Then
        Assertions.assertEquals(course1,result);
    }

    @Test
    void shouldFindAllByName() {
        //Given
        Course course1 = new Course("Math");
        Course course2 = new Course("Physics");
        Course course3 = new Course("Physics");
        Map<UUID, Course> courseMap = new HashMap<>();
        courseMap.put(course1.getCourseId(), course1);
        courseMap.put(course2.getCourseId(), course2);
        courseMap.put(course3.getCourseId(), course3);
        CourseRepository courseRepository = new CourseRepository(courseMap);

        //When
        List<Course> result = courseRepository.findAll("Physics");

        //Then
        Assertions.assertEquals(List.of(course2, course3), result);
    }

    @Test
    void shouldFindAll() {
        //Given
        Course course1 = new Course("Math");
        Course course2 = new Course("Physics");
        Course course3 = new Course("Physics");
        Map<UUID, Course> courseMap = new HashMap<>();
        courseMap.put(course1.getCourseId(), course1);
        courseMap.put(course2.getCourseId(), course2);
        courseMap.put(course3.getCourseId(), course3);
        CourseRepository courseRepository = new CourseRepository(courseMap);

        //When
        List<Course> result = courseRepository.findAll();

        //Then
        Assertions.assertEquals(List.of(course1,course2,course3), result);
//        Assertions.assertEquals(courseMap,result);
    }
}