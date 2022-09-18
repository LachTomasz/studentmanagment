package com.lach.studentmanagment.student;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class StudentController {

    Map<UUID, Student> idToStudent = new HashMap<>();

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        idToStudent.put(student.getId(), student);
        return student;
    }
}
