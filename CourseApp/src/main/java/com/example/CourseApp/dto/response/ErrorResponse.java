package com.example.CourseApp.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/** OpenAPI không hỗ trợ generic nên tạo ra class để khai báo trong doc */
@Getter
@Setter
public class ErrorResponse {

  @Schema(description = "Kết quả thực hiện yêu cầu", defaultValue = "false")
  private Boolean success = Boolean.FALSE;

  @Schema(description = "Thông báo lỗi")
  private String message;

  public static ErrorResponse error(String message) {
    ErrorResponse response = new ErrorResponse();
    response.setMessage(message);
    return response;
  }

}
