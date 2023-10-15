package com.ly.zipcode.infra.repositories;

import java.util.UUID;

public interface IWriteDAO<T> {
  UUID create(T t);

  int update(UUID id, T t);

  void delete(UUID id);
}
