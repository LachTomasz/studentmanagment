package com.lach.studentmanagment.course;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

public class Course {

    private final UUID courseId;
    private final String courseName;

    public Course(@JsonProperty String courseName) {this(UUID.randomUUID(), courseName);}
    public Course(@JsonProperty UUID courseId, @JsonProperty String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (!Objects.equals(courseId, course.courseId)) return false;
        return Objects.equals(courseName, course.courseName);
    }

    @Override
    public int hashCode() {
        int result = courseId != null ? courseId.hashCode() : 0;
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        return result;
    }
}
