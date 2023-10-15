package com.ly.zipcode.infra.repositories;

import java.util.List;
import java.util.UUID;

public interface IReadDAO<T> {
  T findById(UUID id);

  List<T> findAll(int pageSize, int page);
}
