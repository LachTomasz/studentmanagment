package com.lach.studentmanagment.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/students")
    public StudentResponse addStudent(@RequestBody StudentCreateRequest studentCreateRequest) {
        Student student = new Student(studentCreateRequest.getFirstName(), studentCreateRequest.getLastName(), studentCreateRequest.getIndexNumber());
        Student saveStudent = studentService.addStudent(student);
        return new StudentResponse(saveStudent.getId(), saveStudent.getLastName(), Optional.empty());
    }

    @PutMapping("/students/{id}")//podobnie jak w delete uuid pobieram z path variable
    public StudentResponse updateStudent(@PathVariable("id") String id, @RequestBody StudentCreateRequest studentCreateRequest) {
        UUID studentId = UUID.fromString(id);
        Student student = new Student(studentId, studentCreateRequest.getFirstName(), studentCreateRequest.getLastName(), studentCreateRequest.getIndexNumber());
        Student updateStudent = studentService.updateStudent(student);
        return new StudentResponse(updateStudent.getId(), updateStudent.getLastName(), Optional.empty());
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") String id) {
        UUID studentId = UUID.fromString(id);
        studentService.deleteStudent(studentId);
    }

    @GetMapping("/students/{id}")
    public StudentResponse getStudent(@PathVariable("id") String id) {
        UUID studentId = UUID.fromString(id);
        Student getStudent = studentService.findStudent(studentId);
        return new StudentResponse(getStudent.getId(), getStudent.getFirstName(), Optional.empty());
    }

    @GetMapping("/students")
    public Collection<StudentResponse> getAllStudents(@RequestParam(value = "lastName", required = false) String lastName) {

        List<Student> students;
        if (lastName != null) students = studentService.findAllStudent(lastName);
        else students = studentService.findAllStudent();

        return students.stream()
                .map((student) -> (new StudentResponse(student.getId(), student.getLastName(), Optional.empty())))
                .collect(Collectors.toList());

    }
}









