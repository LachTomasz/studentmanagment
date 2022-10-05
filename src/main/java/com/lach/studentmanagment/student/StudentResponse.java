package com.lach.studentmanagment.student;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


public class StudentResponse {

    private final UUID id;
    private final String lastName;
    private final Optional<String> phoneNumber;

    @JsonCreator
    public StudentResponse(@JsonProperty UUID id, @JsonProperty String lastName, @JsonProperty Optional<String> phoneNumber) {
        this.id = id;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public UUID getId() {
        return id;
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
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentResponse that = (StudentResponse) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        return phoneNumber != null ? phoneNumber.equals(that.phoneNumber) : that.phoneNumber == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }
}
