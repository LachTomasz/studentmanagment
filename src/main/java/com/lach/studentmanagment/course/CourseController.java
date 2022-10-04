package com.lach.studentmanagment.course;

import com.lach.studentmanagment.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
public class CourseController {

//    Map<UUID, Course> idToCourse = new HashMap<>();

    private final StudentService studentService;

    @Autowired
    public CourseController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course) {
        Course saveCourse = studentService.addCourses(course);
//        idToCourse.put(course.getCourseId(), course);
//        return idToCourse.get(course.getCourseId());
        return saveCourse;
    }

    @PutMapping("/courses")
    public Course updateCourse(@RequestBody Course course) {
        Course updateCourse = studentService.updateCourse(course);
//        idToCourse.replace(course.getCourseId(), course);
//        return idToCourse.get(course.getCourseId());
        return updateCourse;
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable("id") String id) {
        UUID courseId = UUID.fromString(id);
        studentService.deleteCourse(courseId);
//        idToCourse.remove(courseId);
    }

    @GetMapping("/courses/{id}")
    public Course getCourse(@PathVariable("id") String id) {
        UUID courseId = UUID.fromString(id);
//        return idToCourse.get(courseId);
       Course getCourse = studentService.findCourse(courseId);
       return getCourse;
    }

    @GetMapping("/courses")
    public Collection<Course> getAllCourse(@RequestParam(value = "courseName", required = false) String courseName) {
//        if(courseName!=null) return idToCourse.values().stream()
//                .filter((course) -> course.getCourseName().equals(courseName))
//                .collect(Collectors.toList());
//        else return idToCourse.values().stream().toList();
        if (courseName != null) return studentService.findAllCourse(courseName);
        else return studentService.findAllCourse();
    }
}
