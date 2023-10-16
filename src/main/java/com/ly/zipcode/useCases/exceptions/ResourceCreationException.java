package com.ly.zipcode.useCases.exceptions;

import java.io.Serial;

public class ResourceCreationException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 2022798951001676631L;

  public ResourceCreationException(String message) {
    super(message);
  }
}
