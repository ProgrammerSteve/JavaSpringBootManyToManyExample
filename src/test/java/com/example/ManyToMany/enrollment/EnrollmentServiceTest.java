package com.example.ManyToMany.enrollment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentServiceTest {
    @InjectMocks
    private EnrollmentService enrollmentService;
    @Mock
    private EnrollmentMapper enrollmentMapper;
    @Mock
    private EnrollmentRepository enrollmentRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getAllEnrollments() {
    }
    @Test
    void saveEnrollment() {
    }
    @Test
    void deleteEnrollmentById() {
    }
}