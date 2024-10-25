package com.ventas.models;

public class StockModel extends BaseModel{
    private ArticuloModel articulo;
    private int cantidad;

    public StockModel() {
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

    @Override
    public String toString() {
        return "StockModel{" +
                "articulo=" + articulo +
                ", cantidad=" + cantidad +
                '}';
    }
}
