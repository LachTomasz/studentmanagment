package com.lach.studentmanagment.student;

import java.util.UUID;

public class Student {

    private final UUID id;
    private  final String firstName;
    private final String lastName;
    private final String indexNumber;

    public Student(String firstName, String lastName, String indexNumber) {
        this(UUID.randomUUID(), firstName = firstName, lastName = lastName, indexNumber = indexNumber);

    }

    public Student(UUID id, String firstName, String lastName, String indexNumber) {
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
