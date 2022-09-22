package com.lach.studentmanagment.student;

import com.lach.studentmanagment.course.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldAddStudent() {
        //Given
        String url = "http://localhost:" + port + "/students";
        Student student1 = new Student("Tomek", "Lach", "12546");

        //When
        ResponseEntity<Student> result = restTemplate.postForEntity(url, student1, Student.class);

        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(result.getBody(), student1);
    }

    @Test
    void shouldUpdateStudent() {
        //Given
        String url = "http://localhost:" + port + "/students";
        Student student1 = new Student("Tomek", "Lach", "12546");
        restTemplate.postForEntity(url, student1, Student.class);
        Student updateStudent = new Student(student1.getId(), "Tomek", "Łach", "12546");

        //When
        ResponseEntity<Student> result = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(updateStudent), Student.class, updateStudent);

        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(updateStudent, result.getBody());
    }

    @Test
    void shouldDeleteStudent() {
        //Given
        String urlCreate = "http://localhost:" + port + "/students";
        Student student1 = new Student("Tomek", "Lach", "12546");
        String urlDelete = urlCreate + "/" + student1.getId();
        restTemplate.postForEntity(urlCreate, student1, Student.class);

        //When
        ResponseEntity<Student> result = restTemplate.exchange(urlDelete, HttpMethod.DELETE, new HttpEntity<>(null), Student.class);

        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        Assertions.assertNull(result.getBody());
    }

    @Test
    void shouldGetStudent() {
        //Given
        String urlCreate = "http://localhost:" + port + "/students";
        Student student1 = new Student("Tomek", "Lach", "12546");
        String urlGet = urlCreate + "/" + student1.getId();
        restTemplate.postForEntity(urlCreate, student1, Student.class);

        //When
        ResponseEntity<Student> result = restTemplate.getForEntity(urlGet, Student.class);
        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(student1, result.getBody());
    }

    @Test
    void shouldGetAllStudents() {
        //Given
        String url = "http://localhost:" + port + "/students";
        Student student1 = new Student("Tomek", "Lach", "12546");
        Student student2 = new Student("Michał", "Gurgul", "12786");
        Student student3 = new Student("Katarzyna", "Lach", "12563");
        restTemplate.postForEntity(url, student1, Student.class);
        restTemplate.postForEntity(url, student2, Student.class);
        restTemplate.postForEntity(url, student3, Student.class);
        Student[] idToCourses = new Student[]{student1,student2,student3};

        //When
        ResponseEntity<Student[]> result = restTemplate.getForEntity(url, Student[].class);

        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(result.getBody(), idToCourses);
    }

    @Test
    void shouldGetAllStudentsByName(){
        //Given
        String url = "http://localhost:" + port + "/students";
        URI uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("lastName", "Lach").build().toUri();

        Student student1 = new Student("Tomek", "Lach", "12546");
        Student student2 = new Student("Michał", "Gurgul", "12786");
        Student student3 = new Student("Katarzyna", "Lach", "12563");
        restTemplate.postForEntity(url, student1, Student.class);
        restTemplate.postForEntity(url, student2, Student.class);
        restTemplate.postForEntity(url, student3, Student.class);
        Student[] idToCourses = new Student[]{student1,student3};

        //When
        ResponseEntity<Student[]> result = restTemplate.getForEntity(uri, Student[].class);

        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(idToCourses, result.getBody());
    }
}