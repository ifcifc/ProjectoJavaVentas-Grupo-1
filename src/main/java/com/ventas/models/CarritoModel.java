package com.ventas.models;

import java.util.ArrayList;
import java.util.Optional;

public class CarritoModel extends BaseModel{

    private ArticuloModel articulo;
    private  int cantidad;

    public CarritoModel() {
    }

    public CarritoModel(ArticuloModel articulo, int cantidad) {
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

