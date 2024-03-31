package com.scotiabank.digitalfactory.codechallenge.mapper;

import com.scotiabank.digitalfactory.codechallenge.model.api.StudentRequest;
import com.scotiabank.digitalfactory.codechallenge.model.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)

public interface StudentMapper {
  Student toEntity (StudentRequest studentRequest);
  List<StudentRequest> toRequest (List<Student> students);
}
