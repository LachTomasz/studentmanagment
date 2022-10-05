package com.lach.studentmanagment.student;

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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


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
        StudentCreateRequest student1 = new StudentCreateRequest("Tomek", "Lach", "12546");

        //When
        ResponseEntity<StudentResponse> result = restTemplate.postForEntity(url, student1, StudentResponse.class);

        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        result.getBody().getPhoneNumber().ifPresent(phoneNumber -> System.out.println(phoneNumber));
        result.getBody().getLastName().equals(student1.getLastName());
    }

    @Test
    void shouldUpdateStudent() {
        //Given
        String url = "http://localhost:" + port + "/students";
        StudentCreateRequest student1 = new StudentCreateRequest("Tomek", "Lach", "12546");
        ResponseEntity<StudentResponse> studentResponse = restTemplate.postForEntity(url, student1, StudentResponse.class);

        String studentId =  studentResponse.getBody().getId().toString();
        String updateUrl = "http://localhost:" + port + "/students/" + studentId;
        StudentCreateRequest updateStudent = new StudentCreateRequest("Tomek", "Łach", "12546");

        //When
        ResponseEntity<StudentResponse> result = restTemplate.exchange
                (updateUrl, HttpMethod.PUT, new HttpEntity<>(updateStudent), StudentResponse.class, updateStudent);

        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
//        Assertions.assertEquals(new StudentResponse(updateStudent.getLastName(), Optional.empty()), result.getBody());//bo nie mam UUID
        result.getBody().getLastName().equals(updateStudent.getLastName());
        result.getBody().getPhoneNumber().ifPresent(phoneNumber -> System.out.println(phoneNumber));
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
        StudentCreateRequest student1 = new StudentCreateRequest("Tomek", "Lach", "12546");
        ResponseEntity<StudentResponse> postedStudent = restTemplate.postForEntity(urlCreate, student1, StudentResponse.class);
        String urlGet = urlCreate + "/" + postedStudent.getBody().getId().toString();

        //When
        ResponseEntity<StudentResponse> result = restTemplate.getForEntity(urlGet, StudentResponse.class);
        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        result.getBody().getLastName().equals(student1.getLastName());
        result.getBody().getPhoneNumber().ifPresent(phoneNumber -> System.out.println(phoneNumber));
    }

    @Test
    void shouldGetAllStudents() {
        //Given
        String url = "http://localhost:" + port + "/students";
        StudentCreateRequest student1 = new StudentCreateRequest("Tomek", "Lach", "12546");
        StudentCreateRequest student2 = new StudentCreateRequest("Michał", "Gurgul", "12786");
        StudentCreateRequest student3 = new StudentCreateRequest("Katarzyna", "Lach", "12563");
        restTemplate.postForEntity(url, student1, StudentResponse.class);
        restTemplate.postForEntity(url, student2, StudentResponse.class);
        restTemplate.postForEntity(url, student3, StudentResponse.class);
        StudentCreateRequest[] idToCourses = new StudentCreateRequest[]{student1, student2, student3};

        //When
        ResponseEntity<StudentResponse[]> result = restTemplate.getForEntity(url, StudentResponse[].class);

        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        List<String> wynik1 = Arrays.stream(result.getBody()).map(LastName -> new String(LastName.getLastName())).collect(Collectors.toList());
        List<String> wynik2 = Arrays.stream(idToCourses).map(LastName -> new String(LastName.getLastName())).collect(Collectors.toList());
        Assertions.assertEquals(wynik1.toString(),wynik2.toString());


    }

    @Test
    void shouldGetAllStudentsByName() {
        //Given
        String url = "http://localhost:" + port + "/students";
        URI uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("lastName", "Lach").build().toUri();

        StudentCreateRequest student1 = new StudentCreateRequest("Tomek", "Lach", "12546");
        StudentCreateRequest student2 = new StudentCreateRequest("Michał", "Gurgul", "12786");
        StudentCreateRequest student3 = new StudentCreateRequest("Katarzyna", "Lach", "12563");
        restTemplate.postForEntity(url, student1, StudentResponse.class);
        restTemplate.postForEntity(url, student2, StudentResponse.class);
        restTemplate.postForEntity(url, student3, StudentResponse.class);
        StudentCreateRequest[] idToCourses = new StudentCreateRequest[]{student1, student3};

        //When
        ResponseEntity<StudentResponse[]> result = restTemplate.getForEntity(uri, StudentResponse[].class);

        //Then
        Assertions.assertTrue(result.getStatusCode().is2xxSuccessful());
        List<String> wynik1 = Arrays.stream(result.getBody()).map(LastName -> new String(LastName.getLastName())).collect(Collectors.toList());
        List<String> wynik2 = Arrays.stream(idToCourses).map(LastName -> new String(LastName.getLastName())).collect(Collectors.toList());
        Assertions.assertEquals(wynik1.toString(),wynik2.toString());
    }
}