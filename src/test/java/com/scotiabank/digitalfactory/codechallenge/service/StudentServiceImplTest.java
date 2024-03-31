package com.scotiabank.digitalfactory.codechallenge.service;

import com.scotiabank.digitalfactory.codechallenge.mapper.StudentMapper;
import com.scotiabank.digitalfactory.codechallenge.model.api.StudentRequest;
import com.scotiabank.digitalfactory.codechallenge.model.entity.Student;
import com.scotiabank.digitalfactory.codechallenge.repository.StudentRepository;
import com.scotiabank.digitalfactory.codechallenge.util.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

  @InjectMocks
  private StudentServiceImpl studentService;

  @Mock
  StudentRepository studentRepository;

  @Mock
  StudentMapper studentMapper;

  @Test
  void saveStudent() {
    String studentJson = "request/student.json";
    StudentRequest studentRequest = Utils.convertToObject(studentJson, StudentRequest.class);
    Student student = Utils.convertToObject(studentJson, Student.class);

    when(studentRepository.existsById(studentRequest.getId())).thenReturn(Boolean.FALSE);
    when(studentRepository.save(student)).thenReturn(student);
    when(studentMapper.toEntity(studentRequest)).thenReturn(student);

    StepVerifier.create(studentService.saveStudent(studentRequest))
        .expectNextCount(0)
        .expectComplete()
        .verify();
  }

  @Test
  void saveStudent_Exception() {
    String studentJson = "request/student.json";
    StudentRequest studentRequest = Utils.convertToObject(studentJson, StudentRequest.class);
    Student student = Utils.convertToObject(studentJson, Student.class);

    when(studentRepository.existsById(studentRequest.getId())).thenReturn(Boolean.TRUE);

    StepVerifier.create(studentService.saveStudent(studentRequest))
        .expectError(Exception.class)
        .verify();

    verify(studentRepository, never()).save(student);
  }

  @Test
  void getStudentActives() {
    String studentJson = "request/student.json";
    StudentRequest studentRequest = Utils.convertToObject(studentJson, StudentRequest.class);
    Student student = Utils.convertToObject(studentJson, Student.class);
    List<Student> students = Arrays.asList(student);
    List<StudentRequest> studentsRequest = Arrays.asList(studentRequest);

    when(studentRepository.findByStatus(anyBoolean())).thenReturn(students);
    when(studentMapper.toRequest(students)).thenReturn(studentsRequest);

    Flux<StudentRequest> result = studentService.getStudentActives(anyBoolean());

    StepVerifier.create(result)
        .expectNextCount(students.size())
        .expectComplete()
        .verify();

    verify(studentRepository, times(1)).findByStatus(anyBoolean());
  }
}