package com.ventas.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

public class VentaModel extends CarritoModel{
    private LocalDateTime fecha;

    public VentaModel() {
        this(null, null, 0);
    }
    
    public VentaModel(UsuarioModel usuario, ArticuloModel articuloModel, int cantidad) {
        super(usuario, articuloModel, cantidad);
        this.fecha = LocalDateTime.now();
    }
    
    public VentaModel(CarritoModel carrito) {
        super(carrito.getUsuario(), carrito.getArticulo(), carrito.getCantidad());
        this.fecha = LocalDateTime.now();
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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
