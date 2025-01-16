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
    }

    @Test
    void toStudentNullId() {
    }

    @Test
    void toStudentResponseDto() {
    }
}