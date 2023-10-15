package com.ly.zipcode.utils;

import java.util.regex.Pattern;

public class TextUtils {
  public static String camelCaseToSnakeCase(String text) {
    return Pattern.compile("([a-z])([A-Z])")
        .matcher(text)
        .replaceAll("$1_$2")
        .toLowerCase();
  }
}
