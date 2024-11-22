/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.data;

import com.ventas.models.UsuarioModel;
import com.ventas.services.CarritoService;

/**
 *
 * @author igna
 */
public class SessionDecorator {
    private UsuarioModel usuario;
    private final CarritoService carrito;
     
    public SessionDecorator(UsuarioModel usuario) {
        this.usuario = usuario;
        this.carrito = new CarritoService();
        
        
    }

    public CarritoService getCarrito() {
        return carrito;
    }

    
    
    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
    
    
    
}
