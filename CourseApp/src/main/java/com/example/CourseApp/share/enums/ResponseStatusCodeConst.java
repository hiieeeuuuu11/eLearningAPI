package com.example.CourseApp.share.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseStatusCodeConst {
  SUCCESS(HttpStatus.OK.value(), "Thành công"),
  NO_CHAPTER_FOUND(HttpStatus.NOT_FOUND.value(), "Không tìm thấy chapter nào"),
  NO_CHAPTER_FOUND_FOR_COURSE(HttpStatus.NOT_FOUND.value(), "Không tìm thấy chapter cho khóa học này"),
  CHAPTER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Chapter không tồn tại"),
  NO_COURSE_FOUND(HttpStatus.NOT_FOUND.value(), "Không tìm thấy khóa học!"),
  COURSE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Khóa học không tồn tại với ID: "),
  NO_COURSE_FOUND_FOR_PROVIDER(HttpStatus.NOT_FOUND.value(), "Không tìm thấy khóa học cho nhà cung cấp!"),
  NO_COURSE_FOUND_FOR_TOPIC(HttpStatus.NOT_FOUND.value(), "Không tìm thấy khóa học cho chủ đề!"),
  NO_LESSON_FOUND_FOR_CHAPTER(HttpStatus.NOT_FOUND.value(), "Không tìm thấy bài học cho chương!"),
  LESSON_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Bài học không tồn tại với ID: "),
  NO_PROVIDER_FOUND(HttpStatus.NOT_FOUND.value(), "Không tìm thấy nhà cung cấp!"),
  PROVIDER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Nhà cung cấp không tồn tại với ID: "),
  PROVIDER_EXISTED(HttpStatus.BAD_REQUEST.value(), "Nhà cung cấp đã tồn tại!"),
    NO_TOPIC_FOUND(HttpStatus.NOT_FOUND.value(), "Không tìm thấy chủ đề!"),
    TOPIC_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Chủ đề không tồn tại với ID: "),
  NO_ORDER_FOUND(HttpStatus.NOT_FOUND.value(), "Không tìm thấy hóa đơn!"),
  NO_PAYMENT_FOUND(HttpStatus.NOT_FOUND.value(), "Không tìm thấy thanh toán!"),
  NO_LEARNER_FOUND(HttpStatus.NOT_FOUND.value(), "Không tìm thấy người học!"),
  NO_ENROLLMENT_FOUND(HttpStatus.NOT_FOUND.value(), "Không tìm thấy enrollment!"),
  ;

  private final int httpCode;
  private final String message;
}
