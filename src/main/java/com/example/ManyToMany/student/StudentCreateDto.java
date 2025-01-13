package com.example.ManyToMany.student;

import com.example.ManyToMany.enrollment.Enrollment;

import java.util.Set;

public record StudentCreateDto(
        String name,
        String email,
        String password,
        String role,
        boolean academicProbation,
        Set<Enrollment> enrollments
) {
}