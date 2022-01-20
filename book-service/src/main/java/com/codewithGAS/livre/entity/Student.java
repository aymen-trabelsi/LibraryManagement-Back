package com.codewithGAS.livre.entity;
import lombok.Data;

import javax.persistence.*;

@Data
public class Student {

    private Long studentId ;
    private String firstName;
    private String lastName;
    private String email;
    private int age ;
    private String studyField ;
    private String university ;
    private String phone ;
    private int exist ;


    public Student() {

    }

    public Student(Long studentId, String firstName, String lastName, int age, String studyField, String university, String phone) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.studyField = studyField;
        this.university = university;
        this.phone = phone;
    }
}
