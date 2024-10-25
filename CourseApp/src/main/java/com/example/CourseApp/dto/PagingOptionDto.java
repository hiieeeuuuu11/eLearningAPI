package com.example.CourseApp.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagingOptionDto {

  @Min(value = 1, message = "Limit bắt buộc phải dương")
  @Max(value = 500, message = "Limit không được vượt quá 500")
  @Parameter(name = "limit", description = "Kích thước trang")
  private int limit = 25;

  @Min(value = 0, message = "Page bắt buộc không âm")
  @Parameter(name = "page", description = "Số thứ tự trang")
  private int page = 0;

}
