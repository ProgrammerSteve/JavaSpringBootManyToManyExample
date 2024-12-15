package com.example.ManyToMany.enrollment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        @RequestBody EnrollmentDto dto
    ){
        return enrollmentService.saveEnrollment(dto);
    }


}
