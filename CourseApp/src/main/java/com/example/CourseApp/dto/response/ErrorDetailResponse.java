package com.example.CourseApp.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ErrorDetailResponse {

  @Schema(description = "Kết quả thực hiện yêu cầu", defaultValue = "false")
  private Boolean success = Boolean.FALSE;

  @Schema(description = "Thông báo lỗi")
  private String message;

  @Schema(description = "Mô tả từng loại lỗi")
  private Map<String, String> detail;

  public static ErrorDetailResponse error(String message, Map<String, String> detail) {
    ErrorDetailResponse response = new ErrorDetailResponse();
    response.setMessage(message);
    response.setDetail(detail);
    return response;
  }
}
