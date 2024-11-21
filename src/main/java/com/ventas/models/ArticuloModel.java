package com.ventas.models;

import java.util.UUID;

public class ArticuloModel extends BaseModel implements Comparable<ArticuloModel>{
    private long cod;
    private String nombre, descripcion;
    private double precio;
    private int stock;
    
    public ArticuloModel() {
        super();
    }

    public ArticuloModel(long cod, String nombre, String descripción, double precio, int stock) {
        super();
        this.cod = cod;
        this.nombre = nombre;
        this.descripcion = descripción;
        this.precio = precio;
        this.stock = stock;
    }
    
    public ArticuloModel(UUID id, long cod, String nombre, String descripción, double precio, int stock){
        super(id);
        this.cod = cod;
        this.nombre = nombre;
        this.descripcion = descripción;
        this.precio = precio;
        this.stock = stock;
    }
    
    

    public long getCod() {
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ArticuloModel{id="+ this.getID() + ", cod=" + cod + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", stock=" + stock + '}';
    }

    
    
    @Override
    public int compareTo(ArticuloModel articulo) {
        return this.nombre.compareTo(articulo.nombre);
    }

    
}
