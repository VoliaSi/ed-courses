package com.volia.example.courseservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import com.volia.example.courseservice.dto.CourseResponse;
import com.volia.example.courseservice.dto.CreateCourseRequest;
import com.volia.example.courseservice.dto.UpdateCourseRequest;
import com.volia.example.courseservice.model.Course;
import com.volia.example.courseservice.repository.CourseRepository;
import com.volia.example.courseservice.exception.NotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseResponse createCourse(CreateCourseRequest request) {
        Course course = Course.builder()
                .title(request.title())
                .description(request.description())
                .teacherId(request.teacherId())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Course saved = courseRepository.save(course);
        return mapToResponse(saved);
    }

    public CourseResponse updateCourse(Long id, UpdateCourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found"));

        course = Course.builder()
                .id(course.getId())
                .title(request.title())
                .description(request.description())
                .teacherId(course.getTeacherId())
                .createdAt(course.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();

        return mapToResponse(courseRepository.save(course));
    }

    public List<CourseResponse> getCoursesByTeacher(Long teacherId) {
        return courseRepository.findByTeacherId(teacherId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private CourseResponse mapToResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getTeacherId()
        );
    }
}
