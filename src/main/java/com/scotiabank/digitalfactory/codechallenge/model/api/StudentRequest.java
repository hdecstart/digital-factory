package com.scotiabank.digitalfactory.codechallenge.model.api;

import com.scotiabank.digitalfactory.codechallenge.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class StudentRequest {

  @NotNull
  @Pattern(regexp = "^([0-9]{8}|)$", message = "id Enter 8 digits")
  private String id;

  @NotBlank(message = "name cannot be null")
  private String name;

  @NotBlank(message = "firstName cannot be null")
  private String firstName;

  @NotNull(message = "status cannot be null")
  @Pattern(regexp = Constants.PATTERN_STATUS, message = "status field allowed input: true or false")
  private String status;

  @Min(value = 1, message = "The value must be positive")
  private Integer age;
}
