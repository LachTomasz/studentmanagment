package com.lach.studentmanagment.student;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lach.studentmanagment.course.Course;

import java.util.*;

public class Student {

    private final UUID id;
    private  final String firstName;
    private final String lastName;
    private final String indexNumber;
    private final List<Course> courseIds = new ArrayList<>();

    public Student(@JsonProperty String firstName, @JsonProperty String lastName, @JsonProperty String indexNumber) {
        this(UUID.randomUUID(), firstName = firstName, lastName = lastName, indexNumber = indexNumber);
    }

    @JsonCreator //to musi byc i zawsze tylko jedna na klasę
    public Student(@JsonProperty UUID id, @JsonProperty String firstName, @JsonProperty String lastName, @JsonProperty String indexNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.indexNumber = indexNumber;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public List<Course> getCourseIds() { return courseIds; }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", indexNumber='" + indexNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (!Objects.equals(id, student.id)) return false;
        if (!Objects.equals(firstName, student.firstName)) return false;
        if (!Objects.equals(lastName, student.lastName)) return false;
        if (!Objects.equals(indexNumber, student.indexNumber)) return false;
        return courseIds != null ? courseIds.equals(student.courseIds) : student.courseIds == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (indexNumber != null ? indexNumber.hashCode() : 0);
        result = 31 * result + (courseIds != null ? courseIds.hashCode() : 0);
        return result;
    }
}
