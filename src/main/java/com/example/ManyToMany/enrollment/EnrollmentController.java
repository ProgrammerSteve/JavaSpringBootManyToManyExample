package com.example.ManyToMany.enrollment;

import com.example.ManyToMany.course.CourseService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnrollmentController {

    private EnrollmentService enrollmentService;
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService=enrollmentService;
    }
}
