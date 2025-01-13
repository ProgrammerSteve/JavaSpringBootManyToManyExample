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

    public EnrollmentResponseDto saveEnrollment(EnrollmentCreateDto dto){
        Enrollment enrollment=enrollmentMapper.toEnrollmentNullId(dto);
        Enrollment savedEnrollment=enrollmentRepository.save(enrollment);
        return enrollmentMapper.toEnrollmentResponseDto(savedEnrollment);
    }
    public EnrollmentResponseDto updateEnrollment(EnrollmentDto dto){
        Enrollment enrollment=enrollmentMapper.toEnrollment(dto);
        Enrollment updatedEnrollment=enrollmentRepository.save(enrollment);
        return enrollmentMapper.toEnrollmentResponseDto(updatedEnrollment);
    }

    public void deleteEnrollmentById(int id){
        enrollmentRepository.deleteById(id);
    }

//    public List<List<Integer>> splitIntoBatches(List<Integer> idList, int batchSize){
//
//    }
}
