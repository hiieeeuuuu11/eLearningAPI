package com.example.CourseApp.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultResponse<T> {

  @Schema(description = "Kết quả thực hiện yêu cầu", defaultValue = "true")
  private Boolean success;

  @Schema(description = "Nếu success = `true` thì message `Thành công`, nếu không message sẽ mô tả chi tiết lỗi")
  private String message;

  @Schema(description = "Dữ liệu trả ra")
  private T data;

  public static <T> DefaultResponse<T> success(T data) {
    DefaultResponse<T> response = new DefaultResponse<>();
    response.setSuccess(Boolean.TRUE);
    response.setMessage("Thành công");
    response.setData(data);
    return response;
  }

  public static <T> DefaultResponse<T> error(String message) {
    DefaultResponse<T> response = new DefaultResponse<>();
    response.setSuccess(Boolean.FALSE);
    response.setMessage(message);
    return response;
  }

  public static <T> DefaultResponse<T> error(String message, T data) {
    DefaultResponse<T> response = new DefaultResponse<>();
    response.setSuccess(Boolean.FALSE);
    response.setMessage(message);
    response.setData(data);
    return response;
  }
}
