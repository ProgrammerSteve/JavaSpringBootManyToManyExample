package com.example.ManyToMany.course;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public CourseResponseDto getCourseById(Integer id){
        Optional<Course> courseOptional=courseRepository.findById(id);
        if(courseOptional.isEmpty()){
            throw new CourseNotFoundException("Course by id:"+id+" was not found");
        }
        return courseMapper.toCourseResponseDto(courseOptional.get());

    }

    public CourseResponseDto saveCourse(CourseCreateDto dto){
        Course course=courseMapper.toCourseNullId(dto);
        Course savedCourse= courseRepository.save(course);
        return courseMapper.toCourseResponseDto(savedCourse);
    }

    public CourseResponseDto updateCourse(CourseDto dto){
        Course course=courseMapper.toCourse(dto);
        Course savedCourse= courseRepository.save(course);
        return courseMapper.toCourseResponseDto(savedCourse);
    }

    public Float getCourseGradeAverage(CourseDto dto){
        Course course=courseMapper.toCourse(dto);
        List<Float>grades=course.getEnrollments().stream().map(enrollment -> enrollment.getGrade()).collect(Collectors.toList());
        float sum = grades.stream().reduce(0f, Float::sum);
        int count = grades.size();
        return (count > 0) ? sum / count : 0f;
    }


    public void deleteCourseById(int id){
        courseRepository.deleteById(id);
    }


}
