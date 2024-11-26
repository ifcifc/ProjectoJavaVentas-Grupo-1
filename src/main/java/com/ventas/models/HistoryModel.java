package com.ventas.models;

import java.time.LocalDateTime;

public class HistoryModel<T> extends BaseModel{
    private final LocalDateTime fecha;
    private final T value;
    private final Object meta;

    public HistoryModel(T value) {
        this.value = value;
        this.fecha = LocalDateTime.now();
        this.meta=null;
    }

    public HistoryModel(T value, Object meta) {
        this.value = value;
        this.fecha = LocalDateTime.now();
        this.meta=meta;
    }

    @Override
    public String toString() {
        return "HistoryModel{" + "fecha=" + fecha + ", value=" + value + '}';
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public T getValue() {
        return value;
    }

    public Object getMeta() {
        return meta;
    }
    
    
    
}
