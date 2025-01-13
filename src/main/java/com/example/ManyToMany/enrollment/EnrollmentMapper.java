package com.example.ManyToMany.enrollment;

import org.springframework.stereotype.Service;


@Service
public class EnrollmentMapper {
    public Enrollment toEnrollment(EnrollmentDto dto){
        Enrollment enrollment=new Enrollment();
        enrollment.setId(dto.id());
        enrollment.setStudent(dto.student());
        enrollment.setCourse(dto.course());
        enrollment.setGrade(dto.grade());

        enrollment.setEnrollmentDate(dto.enrollmentDate());
        return enrollment;
    }

    public Enrollment toEnrollmentNullId(EnrollmentCreateDto dto){
        Enrollment enrollment=new Enrollment();

        enrollment.setStudent(dto.student());
        enrollment.setCourse(dto.course());
        enrollment.setGrade(dto.grade());

        enrollment.setEnrollmentDate(dto.enrollmentDate());
        return enrollment;
    }

    public EnrollmentResponseDto toEnrollmentResponseDto(Enrollment enrollment){
        return new EnrollmentResponseDto(
                enrollment.getId(),
                enrollment.getStudent(),
                enrollment.getCourse(),
                enrollment.getGrade(),

                enrollment.getEnrollmentDate()
                );
    }
}
