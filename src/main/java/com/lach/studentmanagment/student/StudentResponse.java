package com.lach.studentmanagment.student;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;


public class StudentResponse {

    public final String firstName;
    public final Optional<String> phoneNumper;
@JsonCreator
    public StudentResponse(@JsonProperty String firstName, @JsonProperty Optional<String> phoneNumper) {
        this.firstName = firstName;
    this.phoneNumper = phoneNumper;
}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentResponse that = (StudentResponse) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return phoneNumper != null ? phoneNumper.equals(that.phoneNumper) : that.phoneNumper == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (phoneNumper != null ? phoneNumper.hashCode() : 0);
        return result;
    }
}
