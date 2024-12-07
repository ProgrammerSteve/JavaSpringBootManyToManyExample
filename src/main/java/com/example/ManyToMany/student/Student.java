package com.example.ManyToMany.student;

import com.example.ManyToMany.enrollment.Enrollment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "student")
    private Set<Enrollment> enrollments;
}
