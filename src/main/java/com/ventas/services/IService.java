package com.ventas.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import com.ventas.models.BaseModel;

public interface IService <T extends BaseModel>{
	Stream<T> getAll();
    T getById(UUID id);
    boolean delete(UUID id);
    boolean update(T model);
    boolean insert(T model);
    boolean any(UUID id);
}
