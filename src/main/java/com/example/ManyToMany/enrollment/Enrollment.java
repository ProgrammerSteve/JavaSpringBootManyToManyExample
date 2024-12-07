package com.example.ManyToMany.enrollment;


import com.example.ManyToMany.course.Course;
import com.example.ManyToMany.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Enrollment {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id",nullable = false)
    private Course course;

    private LocalDate enrollmentDate;
}

