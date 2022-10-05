package com.lach.studentmanagment.student;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class StudentCreateRequest {

    private final Optional<UUID> id;
    private final String firstName;
    private final String lastName;
    private final String indexNumber;

    public StudentCreateRequest(String firstName, String lastName, String indexNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.indexNumber = indexNumber;
        id = Optional.empty();
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

    public Optional<UUID> getId() { return id; }

    @Override
    public String toString() {
        return "StudentCreateRequest{" +
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

        StudentCreateRequest that = (StudentCreateRequest) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(firstName, that.firstName)) return false;
        if (!Objects.equals(lastName, that.lastName)) return false;
        return Objects.equals(indexNumber, that.indexNumber);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (indexNumber != null ? indexNumber.hashCode() : 0);
        return result;
    }
}
