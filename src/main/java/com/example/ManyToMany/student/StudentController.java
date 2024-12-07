package com.example.ManyToMany.student;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    private StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService=studentService;
    }
}
