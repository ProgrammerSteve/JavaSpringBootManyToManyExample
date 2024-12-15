package com.example.ManyToMany.course;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService=courseService;
    }

    @GetMapping("/courses")
    public List<CourseResponseDto> findAllCourses(){
        return courseService.findAllCourses();
    }

    @PostMapping("/courses")
    public CourseResponseDto saveCourse(
            @RequestBody CourseDto dto
    ){
        return courseService.saveCourse(dto);
    }
}
