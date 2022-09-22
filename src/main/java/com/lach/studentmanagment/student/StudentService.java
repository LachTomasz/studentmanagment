package com.lach.studentmanagment.student;

import com.lach.studentmanagment.course.Course;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class StudentService {

   public boolean addCourses(Course course){
      return Collections.emptyList().add(course);
   }

}
