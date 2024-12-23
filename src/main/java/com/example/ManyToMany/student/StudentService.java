package com.example.ManyToMany.student;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository,StudentMapper studentMapper) {
        this.studentRepository=studentRepository;
        this.studentMapper=studentMapper;
    }

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    public StudentResponseDto saveStudent(
            StudentDto dto
    ){
        Student student=studentMapper.toStudent(dto);
        Student savedStudent=studentRepository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public void deleteStudentById(int id){
        studentRepository.deleteById(id);
    }
}
