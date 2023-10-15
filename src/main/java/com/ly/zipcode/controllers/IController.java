package com.ly.zipcode.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface IController<T> {
  @GetMapping("/{id}")
  ResponseEntity<T> findById(@PathVariable UUID id);

  @GetMapping
  ResponseEntity<List<T>> findAll(@RequestParam(required = false, defaultValue = "20") int pageSize,
                                  @RequestParam(required = false, defaultValue = "0") int page);

  @PostMapping
  ResponseEntity<T> create(@RequestBody T t);

  @PutMapping("/{id}")
  ResponseEntity<T> update(@PathVariable UUID id, @RequestBody T t);

  @DeleteMapping(value = "/{id}")
  ResponseEntity<Void> delete(@PathVariable UUID id);
}
