package com.scotiabank.digitalfactory.codechallenge.service;

import com.scotiabank.digitalfactory.codechallenge.mapper.StudentMapper;
import com.scotiabank.digitalfactory.codechallenge.model.api.StudentRequest;
import com.scotiabank.digitalfactory.codechallenge.model.entity.Student;
import com.scotiabank.digitalfactory.codechallenge.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;

  private final StudentMapper mapper;

  public Mono<Void> saveStudent(StudentRequest studentRequest) {
    return Mono.just(studentRepository.existsById(studentRequest.getId()))
        .flatMap(exists -> {
          return validateStudentExists(studentRequest, exists);
        });
  }

  private Mono<Void> validateStudentExists(StudentRequest studentRequest, Boolean exists) {
    if ( exists ) {
      return Mono.error(new Exception("Student ID already exists"));
    } else {
      return Mono.just(studentRepository.save(mapper.toEntity(studentRequest))).then();
    }
  }

  public Flux<StudentRequest> getStudentActives(boolean status) {
    List<Student> lstStudent = studentRepository.findByStatus(status);
    return Flux.fromIterable(mapper.toRequest(lstStudent));
  }
}
