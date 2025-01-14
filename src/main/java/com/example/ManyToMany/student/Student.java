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
    private boolean academicProbation=false;

    @OneToMany(mappedBy = "student")
    private Set<Enrollment> enrollments;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}