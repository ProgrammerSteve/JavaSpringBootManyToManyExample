package com.example.ManyToMany.student;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository=studentRepository;
    }

}
