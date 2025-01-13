package com.example.ManyToMany.enrollment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnrollmentController {
    private EnrollmentService enrollmentService;
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService=enrollmentService;
    }

    @GetMapping("/enrollments")
    public List<EnrollmentResponseDto> findAllEnrollments(){
        return enrollmentService.getAllEnrollments();
    }

    @PostMapping("/enrollments")
    public EnrollmentResponseDto saveEnrollment(
        @RequestBody EnrollmentCreateDto dto
    ){
        return enrollmentService.saveEnrollment(dto);
    }

    @PutMapping("/enrollments")
    public EnrollmentResponseDto updateEnrollment(
            @RequestBody EnrollmentDto dto
    ){
        return enrollmentService.updateEnrollment(dto);
    }

    @DeleteMapping("/enrollments/{enrollment-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEnrollmentById(
            @PathVariable("enrollment-id")int id
    ){
        enrollmentService.deleteEnrollmentById(id);
    }

}
