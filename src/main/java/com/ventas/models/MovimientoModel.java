package com.ventas.models;

import java.time.LocalDateTime;

public class MovimientoModel extends BaseModel{
    private double monto;
    private LocalDateTime fecha;

    private BaseModel from;
    private BaseModel to;

    public MovimientoModel(double monto, LocalDateTime fecha, BaseModel from, BaseModel to) {
        this.monto = monto;
        this.fecha = fecha;
        this.from = from;
        this.to = to;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public BaseModel getFrom() {
        return from;
    }

    public void setFrom(BaseModel from) {
        this.from = from;
    }

    public BaseModel getTo() {
        return to;
    }

    public void setTo(BaseModel to) {
        this.to = to;
    }

    public boolean isIngreso(){
        return this.from==null;
    }

    public boolean isTransferencia(){
        return  this.from instanceof UsuarioModel;
    }

    public boolean isVenta(){
        return this.from instanceof VentaModel;
    }

    @Override
    public String toString() {
        return "MovimientoModel{" +
                "monto=" + monto +
                ", fecha='" + fecha + '\'' +
                ", from=" + ((from==null)?"Ingreso":from) +
                ", to=" + to +
                '}';
    }
}
