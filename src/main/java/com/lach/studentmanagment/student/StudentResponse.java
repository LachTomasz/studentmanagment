package com.lach.studentmanagment.student;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Optional;


public class StudentResponse {

    private final String lastName;
    private final Optional<String> phoneNumber;

    @JsonCreator
    public StudentResponse(@JsonProperty String lastName, @JsonProperty Optional<String> phoneNumber) {
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public Optional<String> getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "StudentResponse{" +
                "lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentResponse that = (StudentResponse) o;

        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        return phoneNumber != null ? phoneNumber.equals(that.phoneNumber) : that.phoneNumber == null;
    }

    @Override
    public int hashCode() {
        int result = lastName != null ? lastName.hashCode() : 0;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }
}
