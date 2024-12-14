package com.example.ManyToMany.course;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService=courseService;
    }

    @GetMapping("/courses")
    public List<Course> findAllCourses(){
        return courseService.findAllCourses();
    }
}
