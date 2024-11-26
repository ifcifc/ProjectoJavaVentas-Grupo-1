package com.ventas.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class VentaModel extends CarritoModel{
    private LocalDateTime fecha;
    private UsuarioModel usuario;
    private double total;
    
    public VentaModel() {
        super(null, null, 0);
    }
    
    public VentaModel(UUID id, UsuarioModel usuario, ArticuloModel articuloModel, LocalDateTime fecha, int cantidad) {
        super(id, articuloModel, cantidad);
        this.fecha = fecha;
        this.usuario = usuario;
        this.total = cantidad*articuloModel.getPrecio();
    }
    
    public VentaModel(UUID id, UsuarioModel usuario, ArticuloModel articuloModel, int cantidad) {
        this(id, usuario, articuloModel, LocalDateTime.now(), cantidad);
    }
    
    
    public VentaModel(UsuarioModel usuario, ArticuloModel articuloModel, LocalDateTime fecha, int cantidad) {
        super(articuloModel, cantidad);
        this.fecha = fecha;
        this.usuario = usuario;
        this.total = cantidad*articuloModel.getPrecio();
    }
    
    public VentaModel(UsuarioModel usuario, ArticuloModel articuloModel, int cantidad) {
        super(articuloModel, cantidad);
        this.fecha = LocalDateTime.now();
        this.usuario = usuario;
        this.total = cantidad*articuloModel.getPrecio();
    }
    
    public VentaModel(UsuarioModel usuario, CarritoModel carrito) {
        super(carrito.getArticulo(), carrito.getCantidad());
        this.fecha = LocalDateTime.now();
        this.usuario = usuario;
        this.total = carrito.getCantidad()*carrito.getArticulo().getPrecio();
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    
    public String getFechaFormat() {
        return fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "VentaModel{" +
                "articulo=" + this.getArticulo() +
                ", cantidad=" + this.getCantidad() +
                ", usuario=" + this.getUsuario() +
                ", fecha=" + fecha +
                ", total=" + total +
                '}';
    }
}
