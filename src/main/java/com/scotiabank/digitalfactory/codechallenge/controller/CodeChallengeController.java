package com.scotiabank.digitalfactory.codechallenge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.scotiabank.digitalfactory.codechallenge.model.api.StudentRequest;
import com.scotiabank.digitalfactory.codechallenge.service.StudentService;
import com.scotiabank.digitalfactory.codechallenge.util.Util;
//import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/digitalfactory/codechallenge/v1")
@Slf4j
@RequiredArgsConstructor
@Validated
public class CodeChallengeController {

  private final StudentService studentService;

  @PostMapping("/student")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Void> saveStudent(
      @Valid @RequestBody StudentRequest student
  ) throws JsonProcessingException {
    log.info("Starting saveStudent {} ", Util.convertJsonToString(student));
    return studentService.saveStudent(student);
  }

  @GetMapping("/student")
  public Flux<StudentRequest> getStudentActives(
      @Valid @RequestParam("status") Boolean status) {
    return studentService.getStudentActives(status);
  }

}
