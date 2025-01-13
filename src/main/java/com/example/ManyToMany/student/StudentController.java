package com.example.ManyToMany.student;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService=studentService;
    }

    @GetMapping("/students")
    public List<StudentResponseDto> findAllStudents(){
        return studentService.findAllStudents();
    }

    @PostMapping("/students")
    public StudentResponseDto saveStudent(
            @RequestBody StudentCreateDto dto
    ){
        return studentService.saveStudent(dto);
    }

    @PutMapping("/students")
    public StudentResponseDto updateStudent(
        @RequestBody StudentDto dto
    ){
        return studentService.updateStudent(dto);
    }

    @GetMapping("/update-academic-probation-all")
    public void updateAcademicProbationAll(){
        studentService.updateAllAcademicProbationStatus();
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentById(
            @PathVariable("student-id")int id
    ){
        studentService.deleteStudentById(id);
    }
}
