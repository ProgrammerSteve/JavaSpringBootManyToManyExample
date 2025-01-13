package com.example.ManyToMany.course;

import com.example.ManyToMany.enrollment.Enrollment;

import java.util.Set;

public record CourseCreateDto(String courseName, String description, Set<Enrollment> enrollments) {
}
