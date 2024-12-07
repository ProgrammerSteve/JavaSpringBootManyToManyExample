package com.example.ManyToMany.course;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private CourseRepository courseRepository;
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository=courseRepository;
    }

    public List<Course> findAllCourses(){
        return courseRepository.findAll();
    }
}
