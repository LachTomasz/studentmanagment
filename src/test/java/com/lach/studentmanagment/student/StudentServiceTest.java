package com.lach.studentmanagment.student;

import com.lach.studentmanagment.course.Course;
import com.lach.studentmanagment.course.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;

import static org.mockito.Mockito.when;

class StudentServiceTest {

    @Test
    void addCourses() {
        //Given
        StudentService studentService = new StudentService(new StudentRepository(new HashMap<>()), new CourseRepository(new HashMap<>()));
        Course course1 = new Course("Math");

        //When
        Course result = studentService.addCourses(course1);

        //Then
        Assertions.assertEquals(course1, result);

    }

    @Test
    void addCoursesWithMock() {
        //Given
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        StudentService studentService = new StudentService(studentRepository, courseRepository);
        Course course1 = new Course("Math");
        when(courseRepository.save(course1)).thenReturn(course1);

        //When
        Course result = studentService.addCourses(course1);

        //Then
        Assertions.assertEquals(course1, result);

    }

    @Test
    void addStudent() {
        //Given
        StudentService studentService = new StudentService(new StudentRepository(new HashMap<>()), new CourseRepository(new HashMap<>()));
        Student student1 = new Student("Jan", "Kowalski", "12345");

        //When
        Student result = studentService.addStudent(student1);

        //Then
        Assertions.assertEquals(student1, result);

    }

    @Test
    void addStudentWithMock() {
        //Given
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        StudentService studentService = new StudentService(studentRepository, courseRepository);
        Student student1 = new Student("Jan", "Kowalski", "12345");
        when(studentRepository.save(student1)).thenReturn(student1);

        //When
        Student  result =  studentService.addStudent(student1);

        //Then
        Assertions.assertEquals(student1, result);

    }
}

