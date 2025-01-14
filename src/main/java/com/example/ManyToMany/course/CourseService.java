package com.example.ManyToMany.course;

import com.example.ManyToMany.enrollment.Enrollment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    public Float getCourseGradeAverage(CourseDto dto) {
        try {
            // Step 1: Convert DTO to Course
            Course course = courseMapper.toCourse(dto);
            if (course == null) {
                System.err.println("Debug: Course object is null after mapping.");
                return 0f; // Return early if the course is null
            }
            System.out.println("Debug: Successfully mapped CourseDto to Course.");
            System.out.println("Debug: Course details - " + course);

            // Step 2: Check if enrollments are initialized
            Set<Enrollment> enrollments = course
                    .getEnrollments();
            if (enrollments == null) {
                System.err.println("Debug: Enrollments set is null for the course.");
                return 0f; // Return early if enrollments are null
            }
            System.out.println("Debug: Enrollments retrieved successfully. Count: " + enrollments.size());

            // Step 3: Extract grades
            List<Float> grades = enrollments.stream()
                    .map(enrollment -> {
                        try {
                            return enrollment.getGrade();
                        } catch (Exception e) {
                            System.err.println("Debug: Error while fetching grade from an enrollment - " + e.getMessage());
                            return null; // Handle null safely if grades are missing
                        }
                    })
                    .filter(grade -> grade != null) // Filter out null grades
                    .collect(Collectors.toList());
            System.out.println("Debug: Extracted grades: " + grades);

            // Step 4: Compute average
            float sum = grades.stream().reduce(0f, Float::sum);
            int count = grades.size();
            System.out.println("Debug: Sum of grades: " + sum + ", Count: " + count);

            return (count > 0) ? sum / count : 0f;
        } catch (Exception e) {
            // Catch any unexpected exceptions
            System.err.println("Debug: An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); // Print the full stack trace for debugging
            return 0f; // Return a default value in case of an error
        }
    }


    public void deleteCourseById(int id){
        courseRepository.deleteById(id);
    }


}
