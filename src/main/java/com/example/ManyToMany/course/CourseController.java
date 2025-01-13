package com.example.ManyToMany.course;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/get-course-average")
    public Float getCourseAverage(
            @RequestBody CourseDto dto
    ){
        return courseService.getCourseGradeAverage(dto);
    }

    @GetMapping("/courses/{course-id}")
    public CourseResponseDto getCourseById(
            @PathVariable Integer id
    ){
        return courseService.getCourseById(id);
    }

    @PostMapping("/courses")
    public CourseResponseDto saveCourse(
            @RequestBody CourseCreateDto dto
    ){
        return courseService.saveCourse(dto);
    }

    @PutMapping("/courses")
    public CourseResponseDto updateCourse(
            @RequestBody CourseDto dto
    ){
        return courseService.updateCourse(dto);
    }

    @DeleteMapping("/courses/{course-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCourseById(
            @PathVariable("course-id")int id
    ){
        courseService.deleteCourseById(id);
    }
}
