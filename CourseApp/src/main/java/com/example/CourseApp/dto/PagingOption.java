package com.example.CourseApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagingOption {

  private Integer page = 0;
  private Integer limit = 100;
  public static PagingOption first() {
    return new PagingOption(0, 1);
  }
}
