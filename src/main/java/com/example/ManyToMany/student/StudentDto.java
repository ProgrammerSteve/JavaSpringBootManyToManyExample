package com.example.ManyToMany.student;

import com.example.ManyToMany.enrollment.Enrollment;

import java.time.LocalDateTime;
import java.util.Set;

public record StudentDto(
        Integer id,
        String name,
        String email,
        String password,
        String role,
        Set<Enrollment> enrollments
) {
}
