package com.example.ManyToMany.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService=studentService;
    }

    @GetMapping("/students")
    public List<Student> findAllStudents(){
        return studentService.findAllStudents();
    }

    @PostMapping("/students")
    public StudentResponseDto saveStudent(
            @RequestBody StudentDto dto
    ){
        return studentService.saveStudent(dto);
    }
}
