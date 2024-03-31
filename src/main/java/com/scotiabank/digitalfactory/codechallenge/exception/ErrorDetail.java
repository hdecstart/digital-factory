package com.scotiabank.digitalfactory.codechallenge.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetail {

  private String defaultMessage;
  private String objectName;
  private String field;

  public ErrorDetail(String defaultMessage, String objectName, String field) {
    this.defaultMessage = defaultMessage;
    this.objectName = objectName;
    this.field = field;
  }
}
