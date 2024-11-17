package com.ventas.models;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class CarritoModel extends BaseModel{

    private ArticuloModel articulo;
    private  int cantidad;

    public CarritoModel() {
    }

    public CarritoModel(ArticuloModel articulo) {
        this(articulo, 0);
    }
    
    public CarritoModel(ArticuloModel articulo, int cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
    }
    
    public CarritoModel(UUID id, ArticuloModel articulo, int cantidad) {
        super(id);
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public ArticuloModel getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloModel articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
    @Override
    public String toString() {
        return "CarritoModel{" +
                "articulo=" + articulo +
                ", cantidad=" + cantidad +
                '}';
    }
}

