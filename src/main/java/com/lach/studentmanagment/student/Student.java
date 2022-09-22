package com.lach.studentmanagment.student;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lach.studentmanagment.course.Course;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Student {

    private final UUID id;
    private  final String firstName;
    private final String lastName;
    private final String indexNumber;
//    3. Dodaj do Studenta liste courseIds przechowujaca kursy na ktore on jest zapisany
//    private final Map<UUID, Course> courseIds = new HashMap<>();

//    @JsonCreator
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

//    3. Dodaj do Studenta liste courseIds przechowujaca kursy na ktore on jest zapisany
//    public Map<UUID, Course> getCourseIds() {
//        return courseIds;
//    }

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

        if (!id.equals(student.id)) return false;
        if (!firstName.equals(student.firstName)) return false;
        if (!lastName.equals(student.lastName)) return false;
        return indexNumber.equals(student.indexNumber);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + indexNumber.hashCode();
        return result;
    }
}
