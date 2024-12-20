package com.example.ManyToMany.course;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private CourseRepository courseRepository;
    private CourseMapper courseMapper;
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository=courseRepository;
        this.courseMapper=courseMapper;
    }

    public List<CourseResponseDto> findAllCourses(){
        return courseRepository.findAll()
                .stream().map(course->courseMapper.toCourseResponseDto(course))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public CourseResponseDto saveCourse(CourseDto dto){
        Course course=courseMapper.toCourse(dto);
        Course savedCourse= courseRepository.save(course);
        return courseMapper.toCourseResponseDto(savedCourse);
    }


}
