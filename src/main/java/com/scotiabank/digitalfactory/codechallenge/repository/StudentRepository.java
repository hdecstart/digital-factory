package com.scotiabank.digitalfactory.codechallenge.repository;

import com.scotiabank.digitalfactory.codechallenge.model.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
  List<Student> findByStatus(boolean status);

  boolean existsById(String id);
}
