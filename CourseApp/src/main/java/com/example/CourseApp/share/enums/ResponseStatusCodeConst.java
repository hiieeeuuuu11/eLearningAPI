package com.example.CourseApp.share.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseStatusCodeConst {
  SUCCESS(HttpStatus.OK.value(), "Thành công"),;
  private final int httpCode;
  private final String message;
}
