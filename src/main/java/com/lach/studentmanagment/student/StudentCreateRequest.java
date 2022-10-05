package com.lach.studentmanagment.student;

public class StudentCreateRequest {


    public final String firstName;
    public final String lastName;
    public final String indexNumber;

    public StudentCreateRequest(String firstName, String lastName, String indexNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.indexNumber = indexNumber;
    }
}
