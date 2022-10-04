package com.lach.studentmanagment.student;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class StudentController {

    Map<UUID, Student> idToStudent = new HashMap<>();

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        idToStudent.put(student.getId(), student);
        return idToStudent.get(student.getId());
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student){
        idToStudent.replace(student.getId(), student);
        return idToStudent.get(student.getId());
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") String id){
        UUID studentId = UUID.fromString(id);
        idToStudent.remove(studentId);
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable("id") String id){
        UUID studentId = UUID.fromString(id);
        return idToStudent.get(studentId);
    }

    @GetMapping("/students")
    public Collection<Student> getAllStudents(@RequestParam(value = "firstName", required = false) String firstName){
        if(firstName!=null) return idToStudent.values().stream()
                .filter((student) -> student.getFirstName().equals(firstName))
                .collect(Collectors.toList());
        else return idToStudent.values().stream().toList();
    }
}









