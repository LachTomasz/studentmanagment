package com.lach.studentmanagment.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    @Test
    void shouldSaveStudent() {
        //Given
        StudentRepository studentRepository = new StudentRepository(new HashMap<>());
        Student student = new Student("John", "Kowalski", "12343");

        //When
        Student result = studentRepository.save(student);

        //Then
        Assertions.assertEquals(student, result);
    }

    @Test
    void shouldFindStudent() {
        //Given
        Student student1 = new Student("John", "Kowalski", "12343");
        Student student2 = new Student("John", "Nowak", "12345");
        Map<UUID, Student> studentMap = Map.of(student1.getId(), student1, student2.getId(), student2);
        StudentRepository studentRepository = new StudentRepository(studentMap);

        //When
        Student result = studentRepository.find(student1.getId());

        //Then
        Assertions.assertEquals(student1, result);
    }

    @Test
    void shouldUpdateStudent() {
        //Given
        Student student1 = new Student("John", "Kowalski", "12343");
        Student student2 = new Student("John", "Nowak", "12345");
        Map<UUID, Student> studentMap = new HashMap<>();

        studentMap.put(student1.getId(), student1);
        studentMap.put(student2.getId(), student2);
        StudentRepository studentRepository = new StudentRepository(studentMap);

        Student updateStudent = new Student(student1.getId(), "John", "Shmit", "12343");

        //When
        Student result = studentRepository.update(updateStudent);

        //Then
        Assertions.assertEquals(updateStudent, result);
    }

    @Test
    void shouldDeleteStudent() {
        //Given
        Student student1 = new Student("John", "Kowalski", "12343");
        Student student2 = new Student("John", "Nowak", "12345");
        Map<UUID, Student> studentMap = new HashMap<>();

        studentMap.put(student1.getId(), student1);
        studentMap.put(student2.getId(), student2);
        StudentRepository studentRepository = new StudentRepository(studentMap);

        //When
        studentRepository.delete(student1.getId());

        //Then
        Assertions.assertNull(studentRepository.find(student1.getId()));
    }

    @Test
    void shouldFindAllStudentsByName() {
        //Given
        Student student1 = new Student("Janek", "Kowalski", "12343");
        Student student2 = new Student("John", "Nowak", "12345");
        Student student3 = new Student("John", "Nowak", "12346");

        Map<UUID, Student> studentMap = new HashMap<>();
        studentMap.put(student1.getId(), student1);
        studentMap.put(student2.getId(), student2);
        studentMap.put(student3.getId(), student3);
        /*Taka alternatywa
        *  Map<UUID, Student> studentMap = Map.of(student1.getId(), student1,
                                                student2.getId(), student2,
                                                student3.getId(), student3);*/

        StudentRepository studentRepository = new StudentRepository(studentMap);

        //When
        List<Student> result = studentRepository.findAll("John");

        //Then
        Assertions.assertEquals(List.of(student2, student3), result);
    }

    @Test
    void shouldFindAllStudents() {
        //Given
        Student student1 = new Student("Janek", "Kowalski", "12343");
        Student student2 = new Student("John", "Nowak", "12345");
        Student student3 = new Student("John", "Nowak", "12346");

        /*Taka alternatywa
        * Map<UUID, Student> studentMap = new HashMap<>();
        studentMap.put(student1.getId(), student1);
        studentMap.put(student2.getId(), student2);
        studentMap.put(student3.getId(), student3);*/
         Map<UUID, Student> studentMap = Map.of(student1.getId(), student1,
                                                student2.getId(), student2,
                                                student3.getId(), student3);

        StudentRepository studentRepository = new StudentRepository(studentMap);

        //When
        List<Student> result = studentRepository.findAll();

        //Then
        Assertions.assertEquals(List.of(student1, student2, student3), result);
//        Assertions.assertEquals(studentMap, result);
    }
}

//ctrl +w zaznaczam sekcje;
//        ctrl+alt+v extrachuje zmienna