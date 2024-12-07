package com.example.ManyToMany.course;

import com.example.ManyToMany.enrollment.Enrollment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "course_name")
    private String courseName;

    private String description;

    @OneToMany(mappedBy="course")
    private Set<Enrollment>enrollments;
}

