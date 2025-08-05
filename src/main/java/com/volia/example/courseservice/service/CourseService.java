package com.volia.example.courseservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import com.volia.example.courseservice.dto.CourseDto;
import com.volia.example.courseservice.model.Course;
import com.volia.example.courseservice.repository.CourseRepository;
import com.volia.example.courseservice.exception.CourseNotFoundException;
import com.volia.example.courseservice.mapper.CourseMapper;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseDto createCourse(CourseDto courseDto) {
        log.info("Creating course with title: {}", courseDto.title());
        
        Course course = courseMapper.fromDto(courseDto);
        Course saved = courseRepository.save(course);
        log.info("Course created successfully with id: {}", saved.getId());
        
        return courseMapper.toDto(saved);
    }

    public CourseDto updateCourse(Long id, CourseDto courseDto) {
        log.info("Updating course with id: {}", id);
        
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        courseMapper.updateFromDto(courseDto, course);
        Course updated = courseRepository.save(course);
        log.info("Course updated successfully with id: {}", updated.getId());
        
        return courseMapper.toDto(updated);
    }

    @Transactional(readOnly = true)
    public List<CourseDto> getCoursesByTeacher(Long teacherId) {
        log.info("Fetching courses for teacher id: {}", teacherId);
        
        List<CourseDto> courses = courseRepository.findByTeacherId(teacherId)
                .stream()
                .map(courseMapper::toDto)
                .toList();
                
        log.info("Found {} courses for teacher id: {}", courses.size(), teacherId);
        return courses;
    }

    @Transactional(readOnly = true)
    public CourseDto getCourseById(Long id) {
        log.info("Fetching course with id: {}", id);
        
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
                
        log.info("Found course: {}", course.getTitle());
        return courseMapper.toDto(course);
    }

    @Transactional(readOnly = true)
    public List<CourseDto> getAllCourses() {
        log.info("Fetching all courses");
        
        List<CourseDto> courses = courseRepository.findAll()
                .stream()
                .map(courseMapper::toDto)
                .toList();
                
        log.info("Found {} courses", courses.size());
        return courses;
    }


}
