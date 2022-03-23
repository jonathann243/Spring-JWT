package com.example.demo.student;


import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/v1/students")
public class StudentManagementController {
    private static final List<Student> STUDENTS= Arrays.asList(
            new Student(1,"Tony yoka"),
            new Student(2,"Raphael Tronchée"),
            new Student(3,"Jonathan Kanyinda")
    );
    @GetMapping
    public List<Student> getAllStudent(){
        return STUDENTS;
    }
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        System.out.println(student);
    }
    @DeleteMapping(path = "{studentID}")
    public void deleteStudent(@PathVariable("studentID") Integer studentID){
        System.out.println(studentID);
    }
    @PutMapping(path="{studentID}")
    public void updateStudent(@PathVariable("studentID") Integer studentID,@RequestBody Student student){
        System.out.println(String.format("%s %s",student,student));
    }

}
