package com.volia.example.courseservice.dto;

public record CreateCourseRequest(
        String title,
        String description,
        Long teacherId
) {}

