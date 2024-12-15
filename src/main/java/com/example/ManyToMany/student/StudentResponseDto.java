package com.example.ManyToMany.student;

import com.example.ManyToMany.enrollment.Enrollment;

import java.util.Set;

public record StudentResponseDto(
        Integer id,
        String name,
        String email,
        String password,
        String role,
        Set<Enrollment> enrollments
) {
}
