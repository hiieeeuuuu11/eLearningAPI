package com.example.CourseApp.exceptions;

import com.example.CourseApp.dto.response.DefaultResponse;
import com.example.CourseApp.dto.response.ErrorDetailResponse;
import com.example.CourseApp.dto.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

  void logError(Exception ex) {
    log.warn("exception: {} - {}", ex.getMessage(), ex.getStackTrace()[0]);
  }

  /**
   * Xử lý exception do chưa handle được
   *
   * @param e
   * @return
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception e) {
    log.error("Có lỗi xảy ra, vui lòng liên hệ đội dự án để được hỗ trợ!", e);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ErrorResponse.error("Có lỗi xảy ra, vui lòng liên hệ đội dự án để được hỗ trợ!"));
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
    logError(e);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ErrorResponse.error(e.getMessage()));
  }

  /**
   * Xử lý exception do đội dự án throw ra
   *
   * @param e
   * @return
   */
  @ExceptionHandler(CourseAppException.class)
  public ResponseEntity<ErrorResponse> handleWarehouseException(CourseAppException e) {
    logError(e);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ErrorResponse.error(e.getMessage()));
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<DefaultResponse<Object>> handleBadRequestException(BadRequestException e) {
    logError(e);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(DefaultResponse.error(e.getMessage()));
  }

  @ExceptionHandler(MissingRequestHeaderException.class)
  public ResponseEntity<ErrorResponse> handleMissingRequestHeaderException(
      MissingRequestHeaderException ex) {
    String errorMessage = String.format("Trường header '%s' không được để trống", ex.getHeaderName());
    logError(ex);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(ErrorResponse.error(errorMessage));
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<DefaultResponse<Object>> handleMissingServletRequestParameterException(
      MissingServletRequestParameterException e) {
    logError(e);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(DefaultResponse.error(String.format("Tham số %s không được để trống", e.getParameterName()), "missing_request_parameter"));
  }


  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<DefaultResponse<Object>> handleIllegalArgumentException(
      IllegalArgumentException e) {
    logError(e);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(DefaultResponse.error(e.getMessage()));
  }

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleObjectNotFoundException(ObjectNotFoundException e) {
    logError(e);
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ErrorResponse.error(e.getMessage()));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    logError(e);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.error("Request không hợp lệ"));
  }

  /**
   * Xử lý exception do Validation throw ra
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDetailResponse> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            error -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    String message;
    if (!CollectionUtils.isEmpty(errors)) {
      message = String.join("\n", errors.values());
    } else {
      message = "Lỗi dữ liệu";
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ErrorDetailResponse.error(message, errors));
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.error(e.getMessage()));
  }

  @ExceptionHandler(HandlerMethodValidationException.class)
  public ResponseEntity<ErrorResponse> handleHandlerMethodValidationException(HandlerMethodValidationException e) {
    return ResponseEntity.badRequest().body(ErrorResponse.error(e.getAllErrors().getFirst().getDefaultMessage()));
  }

}
