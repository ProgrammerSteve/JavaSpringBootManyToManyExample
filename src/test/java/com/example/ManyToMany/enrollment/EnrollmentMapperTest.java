package com.example.ManyToMany.enrollment;

import com.example.ManyToMany.course.Course;
import com.example.ManyToMany.student.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentMapperTest {
    private EnrollmentMapper mapper=new EnrollmentMapper();
    private Student testStudent;
    private Course testCourse;

    @BeforeEach
    void setUp(){
        mapper=new EnrollmentMapper();
        LocalDateTime now=LocalDateTime.now();
        testStudent=new Student(
                777,
                "John",
                "test@gmail.com",
                "test123",
                "admin",
                false,
                new HashSet<>(),
                now
        );
        testCourse=new Course(
                888,
                "physics",
                "entry level physics",
                new HashSet<>(),
                now
        );
    }

    @Test
    void toEnrollment() {
        LocalDateTime now=LocalDateTime.now();
        EnrollmentDto testEnrollmentDto=new EnrollmentDto(
                123,
                testStudent,
                testCourse,
                3.2f,
                now
        );
        Enrollment testEnrollment=new Enrollment(
            123,
            testStudent,
            testCourse,
            now,
            3.2f,
            now
        );

        Enrollment result=mapper.toEnrollment(testEnrollmentDto);

        Assertions.assertEquals(result.getId(),testEnrollment.getId());
        Assertions.assertEquals(result.getGrade(),testEnrollment.getGrade());
        Assertions.assertEquals(
                result.getStudent().getId(),
                testEnrollment.getStudent().getId()
        );
        Assertions.assertEquals(
                result.getCourse().getId(),
                testEnrollment.getCourse().getId()
        );

    }

    @Test
    void toEnrollmentNullId() {
        LocalDateTime now=LocalDateTime.now();
        EnrollmentCreateDto testCreateEnrollmentDto=new EnrollmentCreateDto(
                testStudent,
                testCourse,
                3.2f,
                now
        );
        Enrollment testEnrollment=new Enrollment(
                null,
                testStudent,
                testCourse,
                now,
                3.2f,
                now
        );
        Enrollment result=mapper.toEnrollmentNullId(testCreateEnrollmentDto);
        Assertions.assertNull(result.getId());
        Assertions.assertEquals(
                result.getStudent().getId(),
                testEnrollment.getStudent().getId()
        );
        Assertions.assertEquals(
                result.getCourse().getId(),
                testEnrollment.getCourse().getId()
        );
        Assertions.assertEquals(result.getGrade(), testEnrollment.getGrade());

    }

    @Test
    void toEnrollmentResponseDto() {
        LocalDateTime now=LocalDateTime.now();
        Enrollment testEnrollment=new Enrollment(
                123,
                testStudent,
                testCourse,
                now,
                3.2f,
                now
        );
        EnrollmentResponseDto testEnrollmentResponseDto=new EnrollmentResponseDto(
                123,
                testStudent,
                testCourse,
                3.2f,
                now
        );
        EnrollmentResponseDto result=mapper.toEnrollmentResponseDto(testEnrollment);

        Assertions.assertEquals(result.id(), testEnrollmentResponseDto.id());
        Assertions.assertEquals(result.grade(), testEnrollmentResponseDto.grade());
        Assertions.assertEquals(
                result.student().getId(),
                testEnrollmentResponseDto.student().getId()
        );
        Assertions.assertEquals(
                result.course().getId(),
                testEnrollmentResponseDto.course().getId()
        );
    }
}