package com.ly.zipcode.useCases.addressDetails;

import java.util.List;
import java.util.UUID;

public interface IUseCase<T> {
  T findById(UUID id);

  List<T> findAll(int pageSize, int size);

  T create(T t);

  T update(UUID id, T t);

  void delete(UUID id);
}
