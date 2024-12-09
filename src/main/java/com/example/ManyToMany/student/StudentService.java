package com.example.ManyToMany.student;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository=studentRepository;
    }


    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

}
