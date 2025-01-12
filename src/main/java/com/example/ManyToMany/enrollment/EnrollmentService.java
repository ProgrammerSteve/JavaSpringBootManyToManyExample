package com.example.ManyToMany.enrollment;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {
    private EnrollmentRepository enrollmentRepository;
    private EnrollmentMapper enrollmentMapper;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, EnrollmentMapper enrollmentMapper) {
        this.enrollmentRepository=enrollmentRepository;
        this.enrollmentMapper=enrollmentMapper;
    }

    public List<EnrollmentResponseDto> getAllEnrollments(){
        return enrollmentRepository.findAll()
                .stream()
                .map(enrollment->enrollmentMapper.toEnrollmentResponseDto(enrollment))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public EnrollmentResponseDto saveEnrollment(EnrollmentDto dto){
        Enrollment enrollment=enrollmentMapper.toEnrollment(dto);
        Enrollment savedEnrollment=enrollmentRepository.save(enrollment);
        return enrollmentMapper.toEnrollmentResponseDto(savedEnrollment);
    }







    public void deleteEnrollmentById(int id){
        enrollmentRepository.deleteById(id);
    }


}
