package com.volia.example.courseservice.dto;

public record CourseResponse(
        Long id,
        String title,
        String description,
        Long teacherId
) {}

