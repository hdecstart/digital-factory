package com.scotiabank.digitalfactory.codechallenge.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ControllerAdviceError {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({WebExchangeBindException.class})
  public ResponseEntity<ValidationError> exceptionBadRequest(WebExchangeBindException ex) {

    List<ErrorDetail> errorDetails = ex.getFieldErrors().stream()
        .map(fieldError -> new ErrorDetail(
            fieldError.getDefaultMessage(),
            fieldError.getObjectName(),
            fieldError.getField()
        ))
        .collect(Collectors.toList());

    ValidationError validationError = new ValidationError(
        errorDetails
    );

    return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);

  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({Exception.class})
  public ResponseEntity<ValidationError> exceptionInternalError(Exception ex) {
    ErrorDetail errorDetail = new ErrorDetail(ex.getMessage(), null, null);
    List<ErrorDetail> lstErrorDetail = new ArrayList<ErrorDetail>();
    lstErrorDetail.add(errorDetail);
    ValidationError validationError = new ValidationError(
        lstErrorDetail
    );

    return new ResponseEntity<>(validationError, HttpStatus.INTERNAL_SERVER_ERROR);

  }
}
