package com.lach.studentmanagment.student;

import com.lach.studentmanagment.course.Course;
import com.lach.studentmanagment.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Course addCourses(Course course) {

        return courseRepository.save(course);
    }


}
