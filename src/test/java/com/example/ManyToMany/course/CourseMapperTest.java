package com.example.ManyToMany.course;

import com.example.ManyToMany.enrollment.Enrollment;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CourseMapperTest {
    private CourseMapper mapper;

    @BeforeEach
    void setUp(){
        mapper=new CourseMapper();
    }

    @Test
    void toCourse() {
        LocalDateTime now=LocalDateTime.now();
        CourseDto testCourseDto=new CourseDto(
                123,
                "physics",
                "An entry level physics class",
                new HashSet<>()
        );
        Course testCourse=new Course(
                123,
                "physics",
                "An entry level physics class",
                new HashSet<>(),
                now
        );
        Course result=mapper.toCourse(testCourseDto);
        Assertions.assertEquals(result.getId(),testCourse.getId());
        Assertions.assertEquals(result.getCourseName(),testCourse.getCourseName());
        Assertions.assertEquals(result.getDescription(),testCourse.getDescription());
    }

    @Test
    void toCourseNullId() {
        LocalDateTime now=LocalDateTime.now();
        CourseCreateDto testCourseCreateDto=new CourseCreateDto(
                "physics",
                "An entry level physics class",
                new HashSet<>()
        );
        Course testCourse=new Course(
                null,
                "physics",
                "An entry level physics class",
                new HashSet<>(),
                now
        );
        Course result=mapper.toCourseNullId(testCourseCreateDto);
        Assertions.assertNull(result.getId());
        Assertions.assertEquals(result.getCourseName(),testCourse.getCourseName());
        Assertions.assertEquals(result.getDescription(),testCourse.getDescription());
    }

    @Test
    void toCourseResponseDto() {
        LocalDateTime now=LocalDateTime.now();
        Course testCourse=new Course(
                123,
                "physics",
                "An entry level physics class",
                new HashSet<>(),
                now
        );
        CourseResponseDto testCourseResponseDto=new CourseResponseDto(
                123,
                "physics",
                "An entry level physics class",
                new HashSet<>()
        );
        CourseResponseDto results=mapper.toCourseResponseDto(testCourse);

        Assertions.assertEquals(results.id(),testCourseResponseDto.id());
        Assertions.assertEquals(results.courseName(),testCourseResponseDto.courseName());
        Assertions.assertEquals(results.description(),testCourseResponseDto.description());
    }
}