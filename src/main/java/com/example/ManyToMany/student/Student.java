package com.example.ManyToMany.student;

import com.example.ManyToMany.enrollment.Enrollment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @Column(
            unique = true,
            nullable = false
    )
    private String email;

    @Column(
            nullable = false
    )
    private String password;

    @Column(
            nullable = false
    )
    private String role;

    @Column(name="academic_probation")
    private boolean academicProbation;

    @OneToMany(mappedBy = "student")
    private Set<Enrollment> enrollments;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;


    public Student(
            String name,
            String email,
            String password,
            String role,
            HashSet<Enrollment> enrollments,
            LocalDateTime createdAt
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.academicProbation=false;
        this.enrollments=enrollments;
        this.createdAt=createdAt;
    }


}