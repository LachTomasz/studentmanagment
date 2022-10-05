package com.lach.studentmanagment.student;

import com.lach.studentmanagment.course.Course;
import com.lach.studentmanagment.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    //tu musisz napisac wszystkie polaczenia do repositoriow ktore potem wykorzystasz
    // w student controlerze oraz course kontrolerze
    // zamiast hashMap ktore sa tam obecnie

    public Course addCourses(Course course) {

        return courseRepository.save(course);
    }

    public Course updateCourse(Course course) {
        return courseRepository.update(course);
    }

    public void deleteCourse(UUID courseId) {
        courseRepository.delete(courseId);
    }

    public Course findCourse(UUID courseId) {
        return courseRepository.find(courseId);
    }

    public List<Course> findAllCourse(String courseName) {
        return courseRepository.findAll(courseName);
    }

    public List<Course> findAllCourse() {
        return courseRepository.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(UUID studentId) {
        return studentRepository.find(studentId);
    }

    public Student updateStudent(Student student) {
        return studentRepository.update(student);
    }

    public void deleteStudent(UUID studentId) {
        studentRepository.delete(studentId);
    }

    public List<Student> findAllStudent(String lastName) {
        return studentRepository.findAll(lastName);
    }

    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }
}
