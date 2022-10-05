package com.lach.studentmanagment.student;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class StudentCreateRequest {

    private final String firstName;
    private final String lastName;
    private final String indexNumber;

    @JsonCreator
    public StudentCreateRequest(@JsonProperty String firstName, @JsonProperty String lastName, @JsonProperty String indexNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.indexNumber = indexNumber;
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
        return "StudentCreateRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", indexNumber='" + indexNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentCreateRequest that = (StudentCreateRequest) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        return indexNumber != null ? indexNumber.equals(that.indexNumber) : that.indexNumber == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (indexNumber != null ? indexNumber.hashCode() : 0);
        return result;
    }
}
