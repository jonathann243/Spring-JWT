package com.example.demo.student;

public class Student {
    private final Integer studentID;
    private final String studentName;

    public Student(Integer studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", studentName='" + studentName + '\'' +
                '}';
    }

    public Integer getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }
}
