package com.example.ManyToMany.enrollment;

import com.example.ManyToMany.course.Course;
import com.example.ManyToMany.student.Student;

import java.time.LocalDateTime;

public record EnrollmentCreateDto(Student student, Course course, Float grade, LocalDateTime enrollmentDate) {
}
