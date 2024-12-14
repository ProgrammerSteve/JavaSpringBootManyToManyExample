package com.example.ManyToMany.course;

import org.springframework.stereotype.Service;


@Service
public class CourseMapper {
    public Course toCourse(CourseDto dto){
        Course course=new Course();
        course.setId(dto.id());
        course.setCourseName(dto.courseName());
        course.setDescription(dto.description());
        course.setCreatedAt(dto.createdAt());
        course.setEnrollments(dto.enrollments());
        return course;
    }

    public CourseResponseDto toCourseResponseDto(Course course){
        return new CourseResponseDto(
                course.getId(), course.getCourseName(), course.getDescription(), course.getEnrollments()
        );
    }
}
