package com.lach.studentmanagment.course;

import com.lach.studentmanagment.student.Student;
import com.lach.studentmanagment.student.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
    void shouldSaveCourseWithMock() {
        //Given
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        Course course = new Course("Fizyka");
        when(courseRepository.save(course)).thenReturn(course);

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
    void shouldUpdateCourseWithMock(){
        //Given
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        Course course1 = new Course("Math");
        when(courseRepository.update(course1)).thenReturn(course1);

        //When
        Course result = courseRepository.update(course1);

        //Then
        Assertions.assertEquals(course1, result);
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
    void shouldDeleteCourseWithMock(){
        //Given
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        Course course1 = new Course("Phisik");
        courseRepository.save(course1);

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
    void shouldFindStudentWithMock() {
        //Given
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        Course course = new Course ("Fizyka");
        when(courseRepository.find(course.getCourseId())).thenReturn(course);

        //When
        Course result = courseRepository.find(course.getCourseId());

        //Then
        Assertions.assertEquals(course, result);
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
    void shouldFindAllByNameWithMock(){
        //Given
        Course course1 = new Course("Physics");
        Course course2 = new Course("Physics");

        List<Course> courseList = new ArrayList<>();
        courseList.add(course1);
        courseList.add(course2);

        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        when(courseRepository.findAll("Physics")).thenReturn(courseList);

        //When
        List<Course> result = courseRepository.findAll("Physics");

        //Then
        Assertions.assertEquals(courseList, result);
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

    @Test
    void shouldFindAllWithMock(){
        //Given
        Course course1 = new Course("Math");
        Course course2 = new Course("Physics");
        Course course3 = new Course("Physics");

        List<Course> courseList = new ArrayList<>();
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);

        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        when(courseRepository.findAll()).thenReturn(courseList);

        //When
        List<Course> result = courseRepository.findAll();

        //Then
        Assertions.assertEquals(courseList, result);
    }
}