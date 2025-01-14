package com.example.ManyToMany.student;

import com.example.ManyToMany.course.Course;
import com.example.ManyToMany.enrollment.Enrollment;
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
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllStudents() {
        Student student1 = new Student(
                1,
                "John Doe",
                "john@example.com",
                "password123",
                "senior",
                false,
                new HashSet<>(),
                LocalDateTime.now()
        );
        Student student2 = new Student(
                2,
                "Jane Smith",
                "jane@example.com",
                "password456",
                "junior",
                false,
                new HashSet<>(),
                LocalDateTime.now()
        );
        StudentResponseDto dto1 = new StudentResponseDto(
                1,
                "John Doe",
                "john@example.com",
                "password123",
                "senior",
                false,
                new HashSet<>()
        );
        StudentResponseDto dto2 = new StudentResponseDto(
                2,
                "Jane Smith",
                "jane@example.com",
                "password456",
                "junior",
                false,
                new HashSet<>()
        );

        Mockito.when(studentRepository.findAll()).thenReturn(List.of(student1, student2));
        Mockito.when(studentMapper.toStudentResponseDto(student1)).thenReturn(dto1);
        Mockito.when(studentMapper.toStudentResponseDto(student2)).thenReturn(dto2);

        List<StudentResponseDto> result = studentService.findAllStudents();

        Assertions.assertNotNull(result, "Result should not be null");
        Assertions.assertEquals(2, result.size(), "Result should contain 2 students");
        Assertions.assertEquals(dto1, result.get(0), "First student should match DTO1");
        Assertions.assertEquals(dto2, result.get(1), "Second student should match DTO2");

        Mockito.verify(studentRepository, Mockito.times(1)).findAll();
        Mockito.verify(studentMapper, Mockito.times(1)).toStudentResponseDto(student1);
        Mockito.verify(studentMapper, Mockito.times(1)).toStudentResponseDto(student2);
    }

    @Test
    void saveStudent() {
        LocalDateTime now=LocalDateTime.now();
        StudentCreateDto dto=new StudentCreateDto(
                "John",
                "test@test.com",
                "123",
                "senior",
                false,
                new HashSet<>());

        Integer nullId=null;
        Student student= new Student(
                nullId,
                "John",
                "test@test.com",
                "123",
                "senior",
                false,
                new HashSet<Enrollment>(),
                now
        );
        Student savedStudent= new Student(
                123,
                "John",
                "test@test.com",
                "123",
                "senior",
                false,
                new HashSet<>(),
                now
        );
        StudentResponseDto responseStudent=new StudentResponseDto(
                123,
                "John",
                "test@test.com",
                "123",
                "senior",
                false,
                new HashSet<>()
        );
        Mockito.when(studentMapper.toStudentNullId(dto)).thenReturn(student);
        Mockito.when(studentRepository.save(student)).thenReturn(savedStudent);
        Mockito.when(studentMapper.toStudentResponseDto(savedStudent)).thenReturn(responseStudent);

        StudentResponseDto testResponseDto=studentService.saveStudent(dto);

        Assertions.assertEquals(dto.name(),testResponseDto.name(),"names should match");
        Assertions.assertEquals(dto.password(),testResponseDto.password());
        Assertions.assertEquals(dto.email(),testResponseDto.email());
        Assertions.assertEquals(dto.academicProbation(),testResponseDto.academicProbation());

        Mockito.verify(studentMapper,Mockito.times(1)).toStudentNullId(dto);
        Mockito.verify(studentRepository,Mockito.times(1)).save(student);
        Mockito.verify(studentMapper,Mockito.times(1)).toStudentResponseDto(savedStudent);
    }

    @Test
    void updateStudent(){
        LocalDateTime now=LocalDateTime.now();
        StudentDto dto=new StudentDto(
                123,
                "John",
                "test@test.com",
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
        Student savedStudent= new Student(
                123,
                "John",
                "test@test.com",
                "123",
                "senior",
                false,
                new HashSet<>(),
                now
        );
        StudentResponseDto responseStudent=new StudentResponseDto(
                123,
                "John",
                "test@test.com",
                "123",
                "senior",
                false,
                new HashSet<>()
        );
        Mockito.when(studentMapper.toStudent(dto)).thenReturn(student);
        Mockito.when(studentRepository.save(student)).thenReturn(savedStudent);
        Mockito.when(studentMapper.toStudentResponseDto(savedStudent)).thenReturn(responseStudent);

        StudentResponseDto testResponseDto=studentService.updateStudent(dto);

        Assertions.assertEquals(dto.id(),testResponseDto.id(),"id should match");
        Assertions.assertEquals(dto.name(),testResponseDto.name(),"names should match");
        Assertions.assertEquals(dto.password(),testResponseDto.password());
        Assertions.assertEquals(dto.email(),testResponseDto.email());
        Assertions.assertEquals(dto.academicProbation(),testResponseDto.academicProbation());

        Mockito.verify(studentMapper,Mockito.times(1)).toStudent(dto);
        Mockito.verify(studentRepository,Mockito.times(1)).save(student);
        Mockito.verify(studentMapper,Mockito.times(1)).toStudentResponseDto(savedStudent);
    }

    @Test
    void isStudentOnAcademicProbationById() {
        Integer studentId=123;
        boolean isOnProbation=true;
        Student responseStudent=new Student(
                studentId,
                "John",
                "test@test.com",
                "123",
                "senior",
                isOnProbation,
                new HashSet<Enrollment>(),
                LocalDateTime.now()
        );
        Optional<Student> optionalResponseStudent=Optional.of(responseStudent);
        Mockito.when(studentRepository.findById(studentId))
                .thenReturn(optionalResponseStudent);
        boolean result= studentService.isStudentOnAcademicProbationById(studentId);
        Assertions.assertTrue(result);
        Mockito.verify(studentRepository,Mockito.times(1)).findById(studentId);
    }

    @Test
    void deleteStudentById() {
        int studentId=123;
        Mockito.doNothing().when(studentRepository).deleteById(studentId);
        studentService.deleteStudentById(studentId);
        Mockito.verify(
                studentRepository,
                Mockito.times(1)
        ).deleteById(studentId);
    }

    @Test
    void updateAllAcademicProbationStatus() {
        Enrollment enrollment1 = new Enrollment(
                1,
                null, // Will set student below
                new Course(1, "Math", "Basic Math Course", new HashSet<>(), LocalDateTime.now()),
                LocalDateTime.now(),
                65.0f, // Grade below threshold
                LocalDateTime.now()
        );
        Enrollment enrollment2 = new Enrollment(
                2,
                null, // Will set student below
                new Course(2, "Science", "Basic Science Course", new HashSet<>(), LocalDateTime.now()),
                LocalDateTime.now(),
                85.0f, // Grade above threshold
                LocalDateTime.now()
        );
        Student student1 = new Student(
                1,
                "John",
                "john@test.com",
                "password123",
                "senior",
                false,
                new HashSet<>(Set.of(enrollment1)),
                LocalDateTime.now()
        );
        Student student2 = new Student(
                2,
                "Jane",
                "jane@test.com",
                "password456",
                "junior",
                false,
                new HashSet<>(Set.of(enrollment2)),
                LocalDateTime.now()
        );
        enrollment1.setStudent(student1);
        enrollment2.setStudent(student2);

        Mockito.when(studentRepository.findAll()).thenReturn(List.of(student1, student2));
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenAnswer(invocation -> invocation.getArgument(0));

        studentService.updateAllAcademicProbationStatus();

        Assertions.assertTrue(student1.isAcademicProbation(), "Student 1 should be on academic probation");
        Assertions.assertFalse(student2.isAcademicProbation(), "Student 2 should not be on academic probation");

        Mockito.verify(studentRepository, Mockito.times(1)).findAll();
        Mockito.verify(studentRepository, Mockito.times(1)).save(student1);
        Mockito.verify(studentRepository, Mockito.never()).save(student2); // Student 2 doesn't need saving
    }
}