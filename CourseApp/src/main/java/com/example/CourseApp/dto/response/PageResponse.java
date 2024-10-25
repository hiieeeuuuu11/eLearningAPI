package com.example.CourseApp.dto.response;

import com.example.CourseApp.dto.PagingOption;
import com.example.CourseApp.dto.PagingOptionDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PageResponse<T> extends DefaultListResponse<T> {

  private Pagination pagination;

  public static <T> PageResponse<T> of(
      Long totalPages, Long page, Long pageSize, Long count, List<T> items) {
    PageResponse<T> res = new PageResponse<>();
    res.success();
    res.pagination = new Pagination();
    res.pagination.setCount(count);
    res.pagination.setTotalPages(totalPages);
    res.pagination.setPageSize(pageSize);
    res.pagination.setPage(page);
    res.setData(items);
    return res;
  }

  public static <T> PageResponse<T> emptyOf(Long page, Long pageSize) {
    return of(0L, page, pageSize, 0L, Collections.emptyList());
  }

  public static <T> PageResponse<T> emptyOf(PagingOption pagingOption) {
    return of(0L, (long) pagingOption.getPage(), (long) pagingOption.getLimit(), 0L, Collections.emptyList());
  }

  public static <T> PageResponse<T> emptyOf(PagingOptionDto pagingOptionDto) {
    return of(0L, (long) pagingOptionDto.getPage(), (long) pagingOptionDto.getLimit(), 0L, Collections.emptyList());
  }

  public static <T> PageResponse<T> error(String message) {
    PageResponse<T> response = new PageResponse<>();
    response.setSuccess(false);
    response.setMessage(message);
    return response;
  }

  public static <T> PageResponse<T> convertPageToPageResponse(Page<T> page) {
    return PageResponse.of(
        (long) page.getTotalPages(),
        (long) page.getNumber(),
        (long) page.getSize(),
        page.getTotalElements(),
        page.getContent());
  }

  public static <D> PageResponse<D> success(Page<?> page, List<D> data) {
    return of((long) page.getTotalPages(), (long) page.getNumber(), (long) page.getSize(),
        page.getTotalElements(), data);
  }

  public static <T> PageResponse<T> of(Page<T> page, List<T> items) {
    return of(
        (long) page.getTotalPages(),
        (long) page.getNumber(),
        (long) page.getSize(),
        page.getTotalElements(),
        items);
  }

  public static <E, R> PageResponse<R> of(Page<E> page, Function<E, R> mapper) {
    return of(
      (long) page.getTotalPages(),
      (long) page.getNumber(),
      (long) page.getSize(),
      page.getTotalElements(),
      CollectionUtils.isEmpty(page.getContent())
        ? Collections.emptyList()
        : page.getContent().stream().map(mapper).toList()
    );
  }
}
