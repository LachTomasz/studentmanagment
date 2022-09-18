package com.lach.studentmanagment.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

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
}