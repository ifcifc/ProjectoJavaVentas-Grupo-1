package com.ventas.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

public class VentaModel extends CarritoModel{
    private LocalDateTime fecha;
    private UsuarioModel usuario;
    
    public VentaModel() {
        super(null, null, 0);
    }
    
    public VentaModel(UUID id, UsuarioModel usuario, ArticuloModel articuloModel, LocalDateTime fecha, int cantidad) {
        super(id, articuloModel, cantidad);
        this.fecha = fecha;
        this.usuario = usuario;
    }
    
    public VentaModel(UUID id, UsuarioModel usuario, ArticuloModel articuloModel, int cantidad) {
        this(id, usuario, articuloModel, LocalDateTime.now(), cantidad);
    }
    
    
    public VentaModel(UsuarioModel usuario, ArticuloModel articuloModel, LocalDateTime fecha, int cantidad) {
        super(articuloModel, cantidad);
        this.fecha = fecha;
        this.usuario = usuario;
    }
    
    public VentaModel(UsuarioModel usuario, ArticuloModel articuloModel, int cantidad) {
        super(articuloModel, cantidad);
        this.fecha = LocalDateTime.now();
        this.usuario = usuario;
    }
    
    public VentaModel(CarritoModel carrito) {
        super(carrito.getArticulo(), carrito.getCantidad());
        this.fecha = LocalDateTime.now();
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    
    
    @Override
    public String toString() {
        return "CarritoModel{" +
                "articulo=" + this.getArticulo() +
                ", cantidad=" + this.getCantidad() +
                ", usuario=" + this.getUsuario() +
                ", fecha=" + fecha +
                '}';
    }
}
