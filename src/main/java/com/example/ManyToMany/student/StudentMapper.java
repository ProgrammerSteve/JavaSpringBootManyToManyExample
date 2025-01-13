package com.example.ManyToMany.student;

public class StudentMapper {
    Student toStudent(StudentDto dto){
        Student student=new Student();
        student.setId(dto.id());
        student.setName(dto.name());
        student.setEmail(dto.email());
        student.setRole(dto.role());
        student.setPassword(dto.password());
        student.setAcademicProbation(dto.academicProbation());
        student.setEnrollments(dto.enrollments());
        return student;
    }
    Student toStudentNullId(StudentCreateDto dto){
        Student student=new Student();
        student.setName(dto.name());
        student.setEmail(dto.email());
        student.setRole(dto.role());
        student.setPassword(dto.password());
        student.setAcademicProbation(dto.academicProbation());
        student.setEnrollments(dto.enrollments());
        return student;
    }

    StudentResponseDto toStudentResponseDto(Student student){
        return new StudentResponseDto(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getRole(),
                student.getPassword(),
                student.isAcademicProbation(),
                student.getEnrollments()
        );
    }
}
