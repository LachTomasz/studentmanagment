package com.lach.studentmanagment.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
public class StudentController {

//    Map<UUID, Student> idToStudent = new HashMap<>();//po przerobkach weg!!

    @Autowired
    StudentService studentService;

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
//        idToStudent.put(student.getId(), student);
//        return idToStudent.get(student.getId());
        Student saveStudent = studentService.addStudent(student);
        return saveStudent;
    }



    @PostMapping("/students2")
    public StudentResponse addStudent2(@RequestBody StudentCreateRequest studentCreateRequest) {
//        idToStudent.put(student.getId(), student);
//        return idToStudent.get(student.getId());


        Student student =new Student(studentCreateRequest.firstName, studentCreateRequest.lastName, studentCreateRequest.indexNumber);
        Student saveStudent = studentService.addStudent(student);
        return new StudentResponse(saveStudent.getFirstName(), Optional.empty());
    }


















    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student) {
//        idToStudent.replace(student.getId(), student);
//        return idToStudent.get(student.getId());
        Student updateStudent = studentService.updateStudent(student);
        return updateStudent;
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") String id) {
        UUID studentId = UUID.fromString(id);
//        idToStudent.remove(studentId);
        studentService.deleteStudent(studentId);
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable("id") String id) {
        UUID studentId = UUID.fromString(id);
//        return idToStudent.get(studentId);
        Student getStudent = studentService.findStudent(studentId);
        return getStudent;
    }

    @GetMapping("/students")
    public Collection<Student> getAllStudents(@RequestParam(value = "lastName", required = false) String lastName) {
//        if (lastName != null) return idToStudent.values().stream()
//                .filter((student) -> student.getLastName().equals(lastName))
//                .collect(Collectors.toList());
//        else return idToStudent.values().stream().toList();
        if(lastName != null) return studentService.findAllStudent(lastName);
        else return studentService.findAllStudent();
    }
}









