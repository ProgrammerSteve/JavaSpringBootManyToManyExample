package com.example.ManyToMany.enrollment;

import com.example.ManyToMany.course.Course;
import com.example.ManyToMany.student.Student;

import java.time.LocalDateTime;

public record EnrollmentDto(Integer id, Student student, Course course, LocalDateTime enrollmentDate) {
}
