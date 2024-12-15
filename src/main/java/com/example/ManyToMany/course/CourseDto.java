package com.example.ManyToMany.course;

import com.example.ManyToMany.enrollment.Enrollment;

import java.time.LocalDateTime;
import java.util.Set;

public record CourseDto(Integer id, String courseName, String description, Set<Enrollment> enrollments) {
}
