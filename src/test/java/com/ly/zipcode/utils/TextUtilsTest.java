package com.ly.zipcode.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextUtilsTest {

  @Test
  void camelCaseToSnakeCase() {
    var camelCase = "camelCase";

    var snakeCase = TextUtils.camelCaseToSnakeCase(camelCase);

    assertEquals("camel_case", snakeCase);
  }
}