package com.ly.zipcode.controllers.exceptions;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 803892566944291953L;

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
