package com.example.CourseApp.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.example.api_course_producer.share.constants.Constants.FAIL_MESSAGE;
import static com.example.api_course_producer.share.constants.Constants.SUCCESS_MESSAGE;

public final class ResponseFactory {

  public static <T> ResponseEntity<BaseResponse<T>> success() {
    BaseResponse<T> response =
        BaseResponse.<T>builder().success(true).message(SUCCESS_MESSAGE).build();
    return ResponseEntity.ok(response);
  }

  public static <T> ResponseEntity<BaseResponse<T>> success(T data) {
    BaseResponse<T> response =
        BaseResponse.<T>builder().success(true).data(data).message(SUCCESS_MESSAGE).build();
    return ResponseEntity.ok(response);
  }

  public static <T> ResponseEntity<BaseResponse<T>> success(T data, String message) {
    BaseResponse<T> response =
        BaseResponse.<T>builder().success(true).message(message).data(data).build();
    return ResponseEntity.ok(response);
  }

  public static <T> ResponseEntity<BaseResponse<T>> fail() {
    BaseResponse<T> response =
        BaseResponse.<T>builder().success(false).message(FAIL_MESSAGE).build();
    return ResponseEntity.ok(response);
  }

  public static <T> ResponseEntity<BaseResponse<T>> fail(HttpStatus httpStatus, T data) {
    BaseResponse<T> response =
        BaseResponse.<T>builder().success(false).data(data).message(FAIL_MESSAGE).build();
    return new ResponseEntity<>(response, httpStatus);
  }

  public static <T> ResponseEntity<BaseResponse<T>> fail(
      HttpStatus httpStatus, T data, String message) {
    BaseResponse<T> response =
        BaseResponse.<T>builder().success(false).data(data).message(message).build();
    return new ResponseEntity<>(response, httpStatus);
  }
}
