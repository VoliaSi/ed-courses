package com.volia.example.courseservice.controller;

import com.volia.example.courseservice.dto.CourseResponse;
import com.volia.example.courseservice.dto.CreateCourseRequest;
import com.volia.example.courseservice.dto.UpdateCourseRequest;
import com.volia.example.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CreateCourseRequest request) {
        return ResponseEntity.ok(courseService.createCourse(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id,
                                                       @RequestBody UpdateCourseRequest request) {
        return ResponseEntity.ok(courseService.updateCourse(id, request));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<CourseResponse>> getCoursesByTeacher(@PathVariable Long teacherId) {
        return ResponseEntity.ok(courseService.getCoursesByTeacher(teacherId));
    }
}

