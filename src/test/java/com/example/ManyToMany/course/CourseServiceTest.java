package com.example.ManyToMany.course;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllCourses() {

    }

    @Test
    void saveCourse() {
    }
}