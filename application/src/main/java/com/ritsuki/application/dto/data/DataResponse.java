package com.ritsuki.application.dto.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse<T> {
  private List<T> content;
  private Integer totalPages;
  private Long totalElements;
}
