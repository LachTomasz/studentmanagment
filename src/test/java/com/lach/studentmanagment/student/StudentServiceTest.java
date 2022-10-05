package com.lach.studentmanagment.student;

import com.lach.studentmanagment.course.Course;
import com.lach.studentmanagment.course.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.mock;
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
    void findCourse(){
        //Given
        StudentService studentService = new StudentService(new StudentRepository(new HashMap<>()), new CourseRepository(new HashMap<>()));
        Course course1 = new Course("Mathematik");
        studentService.addCourses(course1);

        //When
        Course result = studentService.findCourse(course1.getCourseId());

        //Then
        Assertions.assertEquals(course1, result);
    }

    @Test
    void findCourseWithMock(){
        //Given
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        StudentService studentService = new StudentService(studentRepository, courseRepository);

        Course course1 = new Course("Mathematik");
        when(studentService.findCourse(course1.getCourseId())).thenReturn(course1);

        //When
        Course result = studentService.findCourse(course1.getCourseId());

        //Then
        Assertions.assertEquals(course1, result);
    }

    @Test
    void updateCourse(){
        //Given
        StudentService studentService = new StudentService(new StudentRepository(new HashMap<>()), new CourseRepository(new HashMap<>()));
        Course course1 = new Course("Math");
        studentService.addCourses(course1);
        Course updateCourse1 = new Course(course1.getCourseId(),"Mathematik");

        //When
        Course result = studentService.updateCourse(updateCourse1);

        //Then
        Assertions.assertEquals(updateCourse1, result);
    }

    @Test
    void updateCourseWithMock(){
        //Given
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        StudentService studentService = new StudentService(studentRepository, courseRepository);
        Course course1 = new Course("Math");
        when(studentService.updateCourse(course1)).thenReturn(course1);

        //When
        Course result = studentService.updateCourse(course1);

        //Then
        Assertions.assertEquals(course1, result);
    }
    @Test
    void deleteCourse(){
        //Given
        StudentService studentService = new StudentService(new StudentRepository(new HashMap<>()), new CourseRepository(new HashMap<>()));
        Course course1 = new Course("Math");
        studentService.addCourses(course1);

        //When
        studentService.deleteCourse(course1.getCourseId());

        //Then
        Assertions.assertNull(studentService.findCourse(course1.getCourseId()));
    }

    @Test
    void deleteSCourseWithMock(){
        //Given
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        StudentService studentService = new StudentService(studentRepository, courseRepository);
        Course course1 = new Course("Math");
        studentService.addCourses(course1);

        //When
        studentService.deleteStudent(course1.getCourseId());

        //Then
        Assertions.assertNull(studentService.findCourse(course1.getCourseId()));
    }

    @Test
    void findAllCourseByName(){
        //Given
        StudentService studentService = new StudentService(new StudentRepository(new HashMap<>()), new CourseRepository(new HashMap<>()));
        Course course1 = new Course("Physics");
        Course course2 = new Course("Physics");
        Course course3 = new Course("Math");
        studentService.addCourses(course1);
        studentService.addCourses(course2);
        studentService.addCourses(course3);

        //When
        List<Course> result = studentService.findAllCourse("Physics");

        //Then
        Assertions.assertEquals(List.of(course1,course2), result);
    }

    @Test
    void findAllCourseByNameWithMock(){
        //Given
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        StudentService studentService = new StudentService(studentRepository, courseRepository);
        Course course1 = new Course("Physics");
        Course course2 = new Course("Physics");
        List<Course> courseList = new ArrayList<>();
        courseList.add(course1);
        courseList.add(course2);
        when(studentService.findAllCourse("Physics")).thenReturn(courseList);

        //When
        List<Course> result = studentService.findAllCourse("Physics");

        //Then
        Assertions.assertEquals(courseList, result);
    }

    @Test
    void findAllCourse(){
        //Given
        StudentService studentService = new StudentService(new StudentRepository(new HashMap<>()), new CourseRepository(new HashMap<>()));
        Course course1 = new Course("Physics");
        Course course2 = new Course("Physics");
        Course course3 = new Course("Math");
        studentService.addCourses(course1);
        studentService.addCourses(course2);
        studentService.addCourses(course3);

        //When
        List<Course> result = studentService.findAllCourse();

        //Then
        Assertions.assertEquals(List.of(course1,course2,course3), result);
    }

    @Test
    void findAllCourseWithMock(){
        //Given
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        StudentService studentService = new StudentService(studentRepository, courseRepository);
        Course course1 = new Course("Physics");
        Course course2 = new Course("Physics");
        Course course3 = new Course("Math");
        List<Course> courseList = new ArrayList<>();
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        when(studentService.findAllCourse()).thenReturn(courseList);

        //When
        List<Course> result = studentService.findAllCourse();

        //Then
        Assertions.assertEquals(courseList, result);
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

    @Test
    void findStudent(){
        //Given
        StudentService studentService = new StudentService(new StudentRepository(new HashMap<>()), new CourseRepository(new HashMap<>()));
        Student student1 = new Student("Jan", "Kowalski", "12345");
        studentService.addStudent(student1);

        //When
        Student result = studentService.findStudent(student1.getId());

        //Then
        Assertions.assertEquals(student1,result);
    }

    @Test
    void findStudentWithMock(){
        //Given
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        StudentService studentService = new StudentService(studentRepository, courseRepository);
        Student student1 = new Student("Jan", "Kowalski", "12345");

        when(studentRepository.find(student1.getId())).thenReturn(student1);

        //When
        Student result = studentService.findStudent(student1.getId());

        //Then
        Assertions.assertEquals(student1, result);
    }

    @Test
    void updateStudent(){
        //Given
        StudentService studentService = new StudentService(new StudentRepository(new HashMap<>()), new CourseRepository(new HashMap<>()));
        Student student1 = new Student("Jan", "Kowalski", "12345");
        studentService.addStudent(student1);
        Student updateStudent1 = new Student(student1.getId(),"Jan", "Kowalczyk", "12345");

        //When
        Student result = studentService.updateStudent(updateStudent1);

        //Then
        Assertions.assertEquals(updateStudent1, result);
    }

    @Test
    void updateStudentWithMock(){
        //Given
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        StudentService studentService = new StudentService(studentRepository, courseRepository);
        Student student1 = new Student("Jan", "Kowalski", "12345");
        when(studentService.updateStudent(student1)).thenReturn(student1);

        //When
        Student result = studentService.updateStudent(student1);

        //Then
        Assertions.assertEquals(student1, result);
    }

    @Test
    void deleteStudent(){
        //Given
        StudentService studentService = new StudentService(new StudentRepository(new HashMap<>()), new CourseRepository(new HashMap<>()));
        Student student1 = new Student("Jan", "Kowalski", "12345");
        studentService.addStudent(student1);

        //When
        studentService.deleteStudent(student1.getId());

        //Then
        Assertions.assertNull(studentService.findStudent(student1.getId()));
    }

    @Test
    void deleteStudentWithMock(){
        //Given
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        StudentService studentService = new StudentService(studentRepository, courseRepository);
        Student student1 = new Student("Jan", "Kowalski", "12345");
        studentService.addStudent(student1);

        //When
        studentService.deleteStudent(student1.getId());

        //Then
        Assertions.assertNull(studentService.findStudent(student1.getId()));
    }

    @Test
    void findAllStudentsByName(){
        //Given
        StudentService studentService = new StudentService(new StudentRepository(new HashMap<>()), new CourseRepository(new HashMap<>()));
        Student student1 = new Student("Janek", "Kowalski", "12343");
        Student student2 = new Student("John", "Nowak", "12345");
        Student student3 = new Student("John", "Nowak", "12346");
        studentService.addStudent(student1);
        studentService.addStudent(student2);
        studentService.addStudent(student3);

        //When
        List<Student> result = studentService.findAllStudent("John");

        //Then
        Assertions.assertEquals(List.of(student2,student3), result);
    }

    @Test
    void findAllStudentsByNameWithMock(){
        //Given
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        StudentService studentService = new StudentService(studentRepository, courseRepository);
        Student student1 = new Student("John", "Nowak", "12345");
        Student student2 = new Student("John", "Nowak", "12346");
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        when(studentService.findAllStudent("John")).thenReturn(studentList);

        //When
        List<Student> result = studentService.findAllStudent("John");

        //Then
        Assertions.assertEquals(studentList, result);
    }

    @Test
    void findAllStudents(){
        //Given
        StudentService studentService = new StudentService(new StudentRepository(new HashMap<>()), new CourseRepository(new HashMap<>()));
        Student student1 = new Student("Janek", "Kowalski", "12343");
        Student student2 = new Student("John", "Nowak", "12345");
        Student student3 = new Student("John", "Nowak", "12346");
        studentService.addStudent(student1);
        studentService.addStudent(student2);
        studentService.addStudent(student3);

        //When
        List<Student> result = studentService.findAllStudent();

        //Then
        Assertions.assertEquals(List.of(student1,student2,student3), result);
    }

    @Test
    void findAllStudentsWithMock(){
        //Given
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        StudentService studentService = new StudentService(studentRepository, courseRepository);
        Student student1 = new Student("John", "Nowak", "12345");
        Student student2 = new Student("John", "Nowak", "12346");
        Student student3 = new Student("Janek", "Nowak", "15346");
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        when(studentService.findAllStudent()).thenReturn(studentList);

        //When
        List<Student> result = studentService.findAllStudent();

        //Then
        Assertions.assertEquals(studentList, result);
    }
}