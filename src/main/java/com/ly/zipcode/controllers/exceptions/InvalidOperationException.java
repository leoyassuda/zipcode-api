package com.ly.zipcode.controllers.exceptions;

import java.io.Serial;

public class InvalidOperationException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = -4326108699925250433L;

  public InvalidOperationException(String message) {
    super(message);
  }
}
