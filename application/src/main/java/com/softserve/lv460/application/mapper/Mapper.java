package com.softserve.lv460.application.mapper;

public interface Mapper<E, D> {
  E toEntity(D dto);
  D toDto(E entity);
}
