package com.lach.studentmanagment.student;

import org.springframework.stereotype.Repository;

import java.util.*;

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

    // update - replace instance of object Student
    public Student update(Student student){
        idToStudent.put(student.getId(), student); //z replace nie dziala
        //a tu nie powinno się pobrac z mapy i to rzucic w return
        return idToStudent.get(student.getId());
//        return student;
    }

    //delete - remove instance of object Student of ID number
    public void delete(UUID id){idToStudent.remove(id);}

    //findAll - returns list of all students with that same first name
    public List<Student> findAll(String firstName){//todo przerob na stream
        List<Student> studentList = new LinkedList<>();
        for (Student student: idToStudent.values()) {
            if(student.getFirstName().equals(firstName)){
                studentList.add(student);
            }
        }
        return studentList;
    }

    //Overload findAll - returns list of all students in repository
    public List<Student> findAll(){return new LinkedList<>(idToStudent.values());}

}
