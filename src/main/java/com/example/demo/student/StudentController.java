package com.example.demo.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    private static final List<Student> STUDENTS= Arrays.asList(
            new Student(1,"Tony yoka"),
            new Student(2,"Raphael Tronchée"),
            new Student(3,"Jonathan Kanyinda")
    );
    @GetMapping(path="{studentId}")
    public Student getStudent(@PathVariable ("studentId") Integer studentId){
     return STUDENTS.stream()
             .filter( student -> studentId.equals(student.getStudentID()))
             .findFirst()
             .orElseThrow(()->new IllegalArgumentException("Student"+studentId+"does not exists"));
    }
    @GetMapping(path="")
    public List<Student> getStudents( Integer studentId){
        return STUDENTS;
    }
}
