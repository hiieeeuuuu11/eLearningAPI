package com.example.CourseApp.exceptions;

import com.example.CourseApp.share.enums.ResponseStatusCodeConst;

public class BadRequestException extends CourseAppException {
  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }

  public BadRequestException(ResponseStatusCodeConst responseStatusCodeConst) {
    super(responseStatusCodeConst);
  }

  public BadRequestException(ResponseStatusCodeConst responseStatusCodeConst, Object ...params) {
    super(responseStatusCodeConst, params);
  }
}
