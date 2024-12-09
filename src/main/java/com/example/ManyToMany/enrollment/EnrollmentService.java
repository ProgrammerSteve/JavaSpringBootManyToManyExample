package com.example.ManyToMany.enrollment;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    private EnrollmentRepository enrollmentRepository;
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository=enrollmentRepository;
    }

    public List<Enrollment> getAllEnrollments(){
        return enrollmentRepository.findAll();
    }
}
