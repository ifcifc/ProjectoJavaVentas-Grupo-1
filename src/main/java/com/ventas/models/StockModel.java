package com.ventas.models;

import java.util.UUID;

public class StockModel extends BaseModel{
    private ArticuloModel articulo;
    private int cantidad;

    public StockModel() {
    }

    public StockModel(ArticuloModel articulo, int cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
    }
    
    
    public StockModel(UUID id, ArticuloModel articulo, int cantidad) {
        super(id);
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void add(int cantidad){
        this.cantidad+=cantidad;
    }
    public void remove(int cantidad){
        this.cantidad-=cantidad;
        this.cantidad = Math.max(0, cantidad);
    }

    public ArticuloModel getArticulo() {
        return articulo;
    }

    
    
    
    @Override
    public String toString() {
        return "StockModel{" +
                "articulo=" + articulo +
                ", cantidad=" + cantidad +
                '}';
    }
}
