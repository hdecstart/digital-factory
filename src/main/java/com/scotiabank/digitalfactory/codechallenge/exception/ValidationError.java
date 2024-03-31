package com.scotiabank.digitalfactory.codechallenge.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidationError {

  private List<ErrorDetail> errors;

  public ValidationError(List<ErrorDetail> errors) {
    this.errors = errors;
  }
}
