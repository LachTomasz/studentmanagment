package com.lach.studentmanagment.student;

import com.lach.studentmanagment.course.Course;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {

    private final Map<UUID, Student> idToStudent;

    public StudentRepository(Map<UUID, Student> idToStudent) {
        this.idToStudent = idToStudent;
    }

    public Student save(Student student){
        idToStudent.put(student.getId(), student);
        //a tu nie powinno się pobrac z mapy i to rzucic w return
        return idToStudent.get(student.getId());
//        return student;
    }

    // findById - it will be search an instance of object Student of ID number
    public Student find(UUID id){return idToStudent.get(id);}

    public Optional<Student> findWithOutNull(UUID id){
        return Optional.ofNullable(idToStudent.get(id));
    }


    // update - replace instance of object Student
    public Student update(Student student){
        idToStudent.put(student.getId(), student); //z replace nie dziala
        //a tu nie powinno się pobrac z mapy i to rzucic w return
        return idToStudent.get(student.getId());
//        return student;
    }

    //delete - remove instance of object Student of ID number
    public void delete(UUID id){idToStudent.remove(id);}

    //findAll - returns list of all students with that same last name
    public List<Student> findAll(String lastName){
        return idToStudent.values().stream()
                .filter((student) -> student.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }

    //Overload findAll - returns list of all students in repository
    public List<Student> findAll(){
        return idToStudent.values().stream().toList();
    }

}