package com.ventas.services;

import java.util.List;
import java.util.UUID;

import com.ventas.models.BaseModel;

public interface IService <T extends BaseModel>{
    List<T> getAll();
    T getById(UUID id);
    boolean delete(UUID id);
    boolean update(T model);
    boolean insert(T model);
    boolean any(UUID id);
}
