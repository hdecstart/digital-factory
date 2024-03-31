package com.scotiabank.digitalfactory.codechallenge.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {

  @Id
  private String id;
  private String name;
  private String firstName;
  private boolean status;
  private Integer age;
}
