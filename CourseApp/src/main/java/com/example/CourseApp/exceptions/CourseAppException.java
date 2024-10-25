package com.example.CourseApp.exceptions;

import com.example.CourseApp.share.enums.ResponseStatusCodeConst;
import lombok.Getter;

@Getter
public abstract class CourseAppException extends RuntimeException {

  private String errorCode = "";

  private static final long serialVersionUID = 1L;

  public CourseAppException(String message){
    super(message);
  }

  protected CourseAppException(String message, Throwable cause) {
    super(message, cause);
  }

  public CourseAppException(ResponseStatusCodeConst responseStatusCodeConst) {
    super(responseStatusCodeConst.getMessage());
    errorCode = responseStatusCodeConst.name().toLowerCase();
  }

  public CourseAppException(ResponseStatusCodeConst responseStatusCodeConst, Object ...params) {
    super(String.format(responseStatusCodeConst.getMessage(), params));
    errorCode = responseStatusCodeConst.name().toLowerCase();
  }

  public CourseAppException(ResponseStatusCodeConst responseStatusCodeConst, Throwable cause) {
    super(responseStatusCodeConst.getMessage(), cause);
    errorCode = responseStatusCodeConst.name().toLowerCase();
  }
}
