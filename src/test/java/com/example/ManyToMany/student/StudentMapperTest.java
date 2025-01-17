package com.example.ManyToMany.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper=new StudentMapper();
    }

    @Test
    void toStudent() {
        LocalDateTime now=LocalDateTime.now();
        StudentDto dtoUpdate=new StudentDto(
                123,
                "John Doe",
                "john@example.com",
                "123",
                "senior",
                false,
                new HashSet<>());
        Student student= new Student(
                123,
                "John",
                "test@test.com",
                "123",
                "senior",
                false,
                new HashSet<>(),
                now
        );
        Student result=mapper.toStudent(dtoUpdate);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(student.getName(),result.getName());
        Assertions.assertEquals(student.getEmail(),result.getEmail());
        Assertions.assertEquals(student.getPassword(),result.getPassword());
        Assertions.assertEquals(student.getRole(),result.getRole());
        Assertions.assertEquals(student.isAcademicProbation(),result.isAcademicProbation());
    }

    @Test
    void toStudentNullId() {
        LocalDateTime now=LocalDateTime.now();
        StudentCreateDto dtoCreate=new StudentCreateDto(
                "John Doe",
                "john@example.com",
                "123",
                "senior",
                false,
                new HashSet<>());
        Student student= new Student(
                null,
                "John",
                "test@test.com",
                "123",
                "senior",
                false,
                new HashSet<>(),
                now
        );
        Student result=mapper.toStudentNullId(dtoCreate);
        Assertions.assertNull(result.getId());
        Assertions.assertEquals(student.getName(),result.getName());
        Assertions.assertEquals(student.getEmail(),result.getEmail());
        Assertions.assertEquals(student.getPassword(),result.getPassword());
        Assertions.assertEquals(student.getRole(),result.getRole());
        Assertions.assertEquals(student.isAcademicProbation(),result.isAcademicProbation());
    }

    @Test
    void toStudentResponseDto() {
        LocalDateTime now=LocalDateTime.now();
        Student student= new Student(
                123,
                "John",
                "test@test.com",
                "123",
                "senior",
                false,
                new HashSet<>(),
                now
        );
        StudentResponseDto studentResponseDto=new StudentResponseDto(
                123,
                "John Doe",
                "john@example.com",
                "123",
                "senior",
                false,
                new HashSet<>());

        StudentResponseDto result=mapper.toStudentResponseDto(student);
        Assertions.assertNotNull(result.id());
        Assertions.assertEquals(studentResponseDto.name(),result.name());
        Assertions.assertEquals(studentResponseDto.email(),result.email());
        Assertions.assertEquals(studentResponseDto.password(),result.password());
        Assertions.assertEquals(studentResponseDto.role(),result.role());
        Assertions.assertEquals(studentResponseDto.academicProbation(),result.academicProbation());
    };
}