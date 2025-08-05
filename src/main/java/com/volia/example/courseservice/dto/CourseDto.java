package com.volia.example.courseservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CourseDto(
        Long id,
        
        @NotBlank(message = "Title is required")
        @Size(max = 100, message = "Title cannot exceed 100 characters")
        String title,
        
        @Size(max = 500, message = "Description cannot exceed 500 characters")
        String description,
        
        @NotNull(message = "Teacher ID is required")
        Long teacherId
) {}