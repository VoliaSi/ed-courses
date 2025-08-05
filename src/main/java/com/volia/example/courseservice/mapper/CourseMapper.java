package com.volia.example.courseservice.mapper;

import com.volia.example.courseservice.dto.CourseDto;
import com.volia.example.courseservice.model.Course;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course fromDto(CourseDto dto);

    CourseDto toDto(Course course);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(CourseDto dto, @MappingTarget Course course);
}
