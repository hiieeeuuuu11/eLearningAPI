package com.example.CourseApp.exceptions;

import com.example.CourseApp.share.enums.ResponseStatusCodeConst;

public class ObjectNotFoundException extends CourseAppException {

  public ObjectNotFoundException(ResponseStatusCodeConst responseStatusCodeConst) {
    super(responseStatusCodeConst);
  }
}
