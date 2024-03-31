package com.scotiabank.digitalfactory.codechallenge.service;

import com.scotiabank.digitalfactory.codechallenge.model.api.StudentRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {

  Mono<Void> saveStudent(StudentRequest studentRequest);

  Flux<StudentRequest> getStudentActives(boolean status);
}
