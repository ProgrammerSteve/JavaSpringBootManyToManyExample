package com.example.ManyToMany.enrollment;

import com.example.ManyToMany.course.Course;
import com.example.ManyToMany.student.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentServiceTest {
    @InjectMocks
    private EnrollmentService enrollmentService;
    @Mock
    private EnrollmentMapper enrollmentMapper;
    @Mock
    private EnrollmentRepository enrollmentRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getAllEnrollments() {
        LocalDateTime now=LocalDateTime.now();
        Course course1 = new Course(1, "Math", "Basic Math Course", new HashSet<>(), now);
        Course course2 = new Course(2, "Science", "Basic Science Course", new HashSet<>(), now);
        Student student1 = new Student(
                1,
                "John Doe",
                "john@example.com",
                "password123",
                "senior",
                false,
                new HashSet<>(),
                now
        );
        Student student2 = new Student(
                2,
                "Jane Smith",
                "jane@example.com",
                "password456",
                "junior",
                false,
                new HashSet<>(),
                now
        );
        Enrollment enrollment1=new Enrollment(1,student1,course1,now,null,now);
        Enrollment enrollment2=new Enrollment(2,student2,course2,now,null,now);

        EnrollmentResponseDto enrollmentDto1=new EnrollmentResponseDto(1,student1,course1,null,now);
        EnrollmentResponseDto enrollmentDto2=new EnrollmentResponseDto(2,student2,course2,null,now);

        Mockito.when(enrollmentRepository.findAll()).thenReturn(List.of(enrollment1,enrollment2));
        Mockito.when(enrollmentMapper.toEnrollmentResponseDto(enrollment1)).thenReturn(enrollmentDto1);
        Mockito.when(enrollmentMapper.toEnrollmentResponseDto(enrollment2)).thenReturn(enrollmentDto2);

        List<EnrollmentResponseDto> result=enrollmentService.getAllEnrollments();
        Assertions.assertEquals(2,result.size());
        Assertions.assertEquals(enrollmentDto1,result.get(0));
        Assertions.assertEquals(enrollmentDto1,result.get(1));

        Assertions.assertNotNull(result, "Result should not be null");
        Mockito.verify(enrollmentRepository,Mockito.times(1)).findAll();
        Mockito.verify(enrollmentMapper,Mockito.times(1)).toEnrollmentResponseDto(enrollment1);
        Mockito.verify(enrollmentMapper,Mockito.times(1)).toEnrollmentResponseDto(enrollment2);
    }
    @Test
    void saveEnrollment() {
        LocalDateTime now=LocalDateTime.now();
        Course course1 = new Course(1, "Math", "Basic Math Course", new HashSet<>(), now);
        Student student1 = new Student(
                1,
                "John Doe",
                "john@example.com",
                "password123",
                "senior",
                false,
                new HashSet<>(),
                now
        );

        EnrollmentCreateDto enrollmentCreateDto=new EnrollmentCreateDto(student1,course1,null,now);
        Enrollment savedEnrollment=new Enrollment(1,student1,course1,now,null,now);
        EnrollmentResponseDto enrollmentDto1=new EnrollmentResponseDto(1,student1,course1,null,now);

        Mockito.when(enrollmentService.saveEnrollment(enrollmentCreateDto)).thenReturn(enrollmentDto1);
        Mockito.when(enrollmentMapper.toEnrollmentNullId(enrollmentCreateDto)).thenReturn(savedEnrollment);
        Mockito.when(enrollmentMapper.toEnrollmentResponseDto(savedEnrollment)).thenReturn(enrollmentDto1);

        EnrollmentResponseDto result=enrollmentService.saveEnrollment(enrollmentCreateDto);

        assertNotNull(result, "Result should not be null");
        assertEquals(enrollmentDto1, result);

        Mockito.verify(enrollmentMapper, Mockito.times(1)).toEnrollmentNullId(enrollmentCreateDto);
        Mockito.verify(enrollmentRepository, Mockito.times(1)).save(Mockito.any(Enrollment.class));
        Mockito.verify(enrollmentMapper, Mockito.times(1)).toEnrollmentResponseDto(savedEnrollment);
    }
    @Test
    void updateEnrollment(){
        LocalDateTime now=LocalDateTime.now();
        Course course1 = new Course(1, "Math", "Basic Math Course", new HashSet<>(), now);
        Student student1 = new Student(
                1,
                "John Doe",
                "john@example.com",
                "password123",
                "senior",
                false,
                new HashSet<>(),
                now
        );

        Enrollment updatedEnrollment=new Enrollment(1,student1,course1,now,null,now);
        EnrollmentDto enrollmentDto=new EnrollmentDto(1,student1,course1,null,now);
        EnrollmentResponseDto enrollmentResponseDto=new EnrollmentResponseDto(1,student1,course1,null,now);

        Mockito.when(enrollmentRepository.save(Mockito.any(Enrollment.class))).thenReturn(updatedEnrollment);
        Mockito.when(enrollmentMapper.toEnrollment(enrollmentDto)).thenReturn(updatedEnrollment);
        Mockito.when(enrollmentMapper.toEnrollmentResponseDto(updatedEnrollment)).thenReturn(enrollmentResponseDto);

        EnrollmentResponseDto result=enrollmentService.updateEnrollment(enrollmentDto);

        Assertions.assertNotNull(result, "Result should not be null");
        Assertions.assertEquals(enrollmentResponseDto, result);

        Mockito.verify(enrollmentMapper, Mockito.times(1)).toEnrollment(enrollmentDto);
        Mockito.verify(enrollmentRepository, Mockito.times(1)).save(Mockito.any(Enrollment.class));
        Mockito.verify(enrollmentMapper, Mockito.times(1)).toEnrollmentResponseDto(updatedEnrollment);
    }
    @Test
    void deleteEnrollmentById() {
        int enrollmentId = 1;
        Mockito.doNothing().when(enrollmentRepository).deleteById(enrollmentId);
        enrollmentService.deleteEnrollmentById(enrollmentId);
        Mockito.verify(enrollmentRepository, Mockito.times(1)).deleteById(enrollmentId);
    }
}