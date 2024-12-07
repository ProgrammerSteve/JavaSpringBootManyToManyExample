package com.example.ManyToMany.enrollment;

import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    private EnrollmentRepository enrollmentRepository;
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository=enrollmentRepository;
    }
}
