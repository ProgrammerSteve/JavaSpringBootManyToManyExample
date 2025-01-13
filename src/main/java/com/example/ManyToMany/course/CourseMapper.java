package com.example.ManyToMany.course;

import org.springframework.stereotype.Service;


@Service
public class CourseMapper {
    public Course toCourse(CourseDto dto){
        Course course=new Course();
        course.setId(dto.id());
        course.setCourseName(dto.courseName());
        course.setDescription(dto.description());
        course.setEnrollments(dto.enrollments());
        return course;
    }

    public Course toCourseNullId(CourseCreateDto dto){
        Course course=new Course();
        course.setCourseName(dto.courseName());
        course.setDescription(dto.description());
        course.setEnrollments(dto.enrollments());
        return course;
    }

    public CourseResponseDto toCourseResponseDto(Course course){
        return new CourseResponseDto(
                course.getId(), course.getCourseName(), course.getDescription(), course.getEnrollments()
        );
    }
}
