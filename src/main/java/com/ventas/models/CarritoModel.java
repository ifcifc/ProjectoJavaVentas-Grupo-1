package com.ventas.models;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class CarritoModel extends BaseModel{

    private ArticuloModel articulo;
    private UsuarioModel usuario;
    private  int cantidad;

    public CarritoModel() {
    }

    public CarritoModel(UsuarioModel usuario, ArticuloModel articulo, int cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.usuario = usuario;
    }

    public CarritoModel(UUID id, UsuarioModel usuario, ArticuloModel articulo, int cantidad) {
        super(id);
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.usuario = usuario;
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

    public UsuarioModel getUsuario() {
        return usuario;
    }
    
    @Override
    public String toString() {
        return "CarritoModel{" +
                "articulo=" + articulo +
                ", cantidad=" + cantidad +
                ", usuario=" + this.getUsuario() +
                '}';
    }
}

