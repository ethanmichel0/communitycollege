package com.communitycollege.DTO;

import java.sql.Time;
import java.util.Set;

public class CourseDTO {
    private int id;
    private String name;
    private Time time;
    private Set<String> enrollment;
    private int enrollmentNum;

    public CourseDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Set<String> getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Set<String> enrollment) {
        this.enrollment = enrollment;
    }

    public int getEnrollmentNum() {
        return enrollmentNum;
    }

    public void setEnrollmentNum(int enrollmentNum) {
        this.enrollmentNum = enrollmentNum;
    }
}
