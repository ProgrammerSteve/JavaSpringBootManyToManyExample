package com.example.ManyToMany.student;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public boolean isStudentOnAcademicProbationById(int id){
        Optional<Student> optionalStudent=studentRepository.findById(id);
        if(optionalStudent.isEmpty()){
            throw new StudentNotFoundException("student with id:"+id+" was not found");
        }
        return optionalStudent.get().isAcademicProbation();
    }

    public void deleteStudentById(int id){
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateAllAcademicProbationStatus() {
        studentRepository.findAll().parallelStream().forEach(student -> {
            boolean onProbation = student.getEnrollments().stream().anyMatch(
                    enrollment -> enrollment.getGrade() != null && enrollment.getGrade() < 70.0
            );
            if (student.isAcademicProbation() != onProbation) {
                student.setAcademicProbation(onProbation);
                studentRepository.save(student);
            }
        });
    }
}
