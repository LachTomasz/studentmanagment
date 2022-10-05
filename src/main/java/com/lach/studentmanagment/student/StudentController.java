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

//    Map<UUID, Student> idToStudent = new HashMap<>();//po przerobkach weg!!

    @Autowired
    StudentService studentService;

//    @PostMapping("/students_OLD")
//    public Student addStudent(@RequestBody Student student) {
////        idToStudent.put(student.getId(), student);
////        return idToStudent.get(student.getId());
//        Student saveStudent = studentService.addStudent(student);
//        return saveStudent;
//    }

    @PostMapping("/students")
    public StudentResponse addStudent(@RequestBody StudentCreateRequest studentCreateRequest) {
        Student student = new Student(studentCreateRequest.getFirstName(), studentCreateRequest.getLastName(), studentCreateRequest.getIndexNumber());
        Student saveStudent = studentService.addStudent(student);
        return new StudentResponse(saveStudent.getLastName(), Optional.empty());
    }

//    @PutMapping("/students_OLD")
//    public Student updateStudent(@RequestBody Student student) {
////        idToStudent.replace(student.getId(), student);
////        return idToStudent.get(student.getId());
//        Student updateStudent = studentService.updateStudent(student);
//        return updateStudent;
//    }

    @PutMapping("/students/{id}")//podobnie jak w delete uuid pobieram z path variable
    public StudentResponse updateStudent(@PathVariable("id") String id, @RequestBody StudentCreateRequest studentCreateRequest) {
        UUID studentId = UUID.fromString(id);
        Student student = new Student(studentId, studentCreateRequest.getFirstName(), studentCreateRequest.getLastName(), studentCreateRequest.getIndexNumber());
        Student updateStudent = studentService.updateStudent(student);
        return new StudentResponse(updateStudent.getLastName(), Optional.empty());
    }


    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") String id) {
        UUID studentId = UUID.fromString(id);
//        idToStudent.remove(studentId);
        studentService.deleteStudent(studentId);
    }

//    @GetMapping("/students_OLD/{id}")
//    public Student getStudent_OLD(@PathVariable("id") String id) {
//        UUID studentId = UUID.fromString(id);
////        return idToStudent.get(studentId);
//        Student getStudent = studentService.findStudent(studentId);
//        return getStudent;
//    }

    @GetMapping("/students/{id}")
    public StudentResponse getStudent(@PathVariable("id") String id) {
        UUID studentId = UUID.fromString(id);
        Student getStudent = studentService.findStudent(studentId);
        return new StudentResponse(getStudent.getFirstName(), Optional.empty());
    }

//    @GetMapping("/students_OLD")
//    public Collection<Student> getAllStudents_OLD(@RequestParam(value = "lastName", required = false) String lastName) {
////        if (lastName != null) return idToStudent.values().stream()
////                .filter((student) -> student.getLastName().equals(lastName))
////                .collect(Collectors.toList());
////        else return idToStudent.values().stream().toList();
//        if (lastName != null) return studentService.findAllStudent(lastName);
//        else return studentService.findAllStudent();
//    }

    @GetMapping("/students")
    public Collection<StudentResponse> getAllStudents(@RequestParam(value = "lastName", required = false) String lastName) {

        List<Student> students;
        if (lastName != null) students = studentService.findAllStudent(lastName);
        else students = studentService.findAllStudent();

        return students.stream()
                .map((student) -> (new StudentResponse(student.getLastName(), Optional.empty())))
                .collect(Collectors.toList());


//        List<StudentResponse> result = new ArrayList<>();
//        if(lastName != null) {
//            List<Student> temp = studentService.findAllStudent(lastName);
//            for (Student student : temp) {
//                StudentResponse studentResponse = new StudentResponse(student.getFirstName(), student.getId(), Optional.empty());
//                result.add(studentResponse);
//            }
//            return result;
//        }
//        else {
//            List<Student> temp = studentService.findAllStudent();
//            for (Student student : temp){
//                StudentResponse studentResponse = new StudentResponse(student.getFirstName(),  student.getId(),Optional.empty());
//                result.add(studentResponse);
//            }
//            return result;
//        }
    }
}









