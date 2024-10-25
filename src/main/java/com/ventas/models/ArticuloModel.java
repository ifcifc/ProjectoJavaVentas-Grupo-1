package com.ventas.models;

public class ArticuloModel extends BaseModel{
    private long cod;
    private String nombre, descripcion;
    private double precio;

    public ArticuloModel() {
    }

    public ArticuloModel(long cod, String nombre, String descripción, double precio) {
        super();
        this.cod = cod;
        this.nombre = nombre;
        this.descripcion = descripción;
        this.precio = precio;
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

    @Override
    public String toString() {
        return "ArticuloModel{" +
                "cod=" + cod +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }
}
