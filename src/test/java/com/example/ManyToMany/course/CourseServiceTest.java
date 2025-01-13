package com.example.ManyToMany.course;

import com.example.ManyToMany.enrollment.Enrollment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    @Test
    void findAllCourses() {
        Course course1 = new Course(1, "Math", "Basic Math Course", new HashSet<>(), LocalDateTime.now());
        Course course2 = new Course(2, "Science", "Basic Science Course", new HashSet<>(), LocalDateTime.now());
        CourseResponseDto dto1 = new CourseResponseDto(1, "Math", "Basic Math Course",new HashSet<>());
        CourseResponseDto dto2 = new CourseResponseDto(2, "Science", "Basic Science Course",new HashSet<>());

        Mockito.when(courseRepository.findAll()).thenReturn(List.of(course1, course2));
        Mockito.when(courseMapper.toCourseResponseDto(course1)).thenReturn(dto1);
        Mockito.when(courseMapper.toCourseResponseDto(course2)).thenReturn(dto2);

        List<CourseResponseDto> result = courseService.findAllCourses();

        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));


        Mockito.verify(courseRepository, Mockito.times(1)).findAll();
        Mockito.verify(courseMapper, Mockito.times(1)).toCourseResponseDto(course1);
        Mockito.verify(courseMapper, Mockito.times(1)).toCourseResponseDto(course2);
    }

    @Test
    void getCourseById() {
        int courseId = 1;
        Course course = new Course(courseId, "Biology", "Advanced Biology Course", new HashSet<>(), LocalDateTime.now());
        CourseResponseDto dto = new CourseResponseDto(courseId, "Biology", "Advanced Biology Course", new HashSet<>());

        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        Mockito.when(courseMapper.toCourseResponseDto(course)).thenReturn(dto);

        CourseResponseDto resultResponseDto = courseService.getCourseById(courseId);

        assertEquals(dto, resultResponseDto);

        Mockito.verify(courseRepository, Mockito.times(1)).findById(courseId);
        Mockito.verify(courseMapper, Mockito.times(1)).toCourseResponseDto(course);
    }

    @Test
    void saveCourse() {
        CourseCreateDto requestDto = new CourseCreateDto("History", "World History Course",new HashSet<Enrollment>());
        Course course = new Course(null,"History", "World History Course", new HashSet<>(), LocalDateTime.now());
        Course savedCourse = new Course(1, "History", "World History Course", new HashSet<>(), LocalDateTime.now());
        CourseResponseDto responseDto = new CourseResponseDto(1, "History", "World History Course", new HashSet<>());

        Mockito.when(courseMapper.toCourseNullId(requestDto)).thenReturn(course);
        Mockito.when(courseRepository.save(course)).thenReturn(savedCourse);
        Mockito.when(courseMapper.toCourseResponseDto(savedCourse)).thenReturn(responseDto);

        CourseResponseDto result = courseService.saveCourse(requestDto);
        assertEquals(responseDto, result);

        Mockito.verify(courseMapper, Mockito.times(1)).toCourseNullId(requestDto);
        Mockito.verify(courseRepository, Mockito.times(1)).save(course);
        Mockito.verify(courseMapper, Mockito.times(1)).toCourseResponseDto(savedCourse);
    }

    @Test
    void updateCourse() {
        CourseDto requestDto = new CourseDto(123,"History", "World History Course",new HashSet<Enrollment>());
        Course course = new Course(null,"History", "World History Course", new HashSet<>(), LocalDateTime.now());
        Course savedCourse = new Course(1, "History", "World History Course", new HashSet<>(), LocalDateTime.now());
        CourseResponseDto responseDto = new CourseResponseDto(1, "History", "World History Course", new HashSet<>());

        Mockito.when(courseMapper.toCourse(requestDto)).thenReturn(course);
        Mockito.when(courseRepository.save(course)).thenReturn(savedCourse);
        Mockito.when(courseMapper.toCourseResponseDto(savedCourse)).thenReturn(responseDto);

        CourseResponseDto result = courseService.updateCourse(requestDto);
        assertEquals(responseDto, result);

        Mockito.verify(courseMapper, Mockito.times(1)).toCourse(requestDto);
        Mockito.verify(courseRepository, Mockito.times(1)).save(course);
        Mockito.verify(courseMapper, Mockito.times(1)).toCourseResponseDto(savedCourse);
    }

    @Test
    void getCourseGradeAverage() {
        int courseId = 1;
        Enrollment enrollment1 = new Enrollment(1, null, null, LocalDateTime.now(), 85.0f, LocalDateTime.now());
        Enrollment enrollment2 = new Enrollment(2, null, null, LocalDateTime.now(), 95.0f, LocalDateTime.now());
        Set<Enrollment> EnrollmentSet=Set.of(enrollment1, enrollment2);
        LocalDateTime now=LocalDateTime.now();
        Course course = new Course(courseId, "Physics", "Intro to Physics", EnrollmentSet, now);
        CourseDto courseDto=new CourseDto(courseId,"Physics","Intro to Physics", EnrollmentSet);
        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        float result = courseService.getCourseGradeAverage(courseDto);
        assertEquals(90.0f, result, 0.01);
        Mockito.verify(courseRepository, Mockito.times(1)).findById(courseId);
    }

    @Test
    void deleteCourseById() {
        int courseId = 1;
        Mockito.doNothing().when(courseRepository).deleteById(courseId);
        courseService.deleteCourseById(courseId);
        Mockito.verify(courseRepository, Mockito.times(1)).deleteById(courseId);
    }
}