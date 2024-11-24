package com.ventas.data;

import com.ventas.models.UsuarioModel;
import com.ventas.services.CarritoService;

/**
 * Este objeto se contiene en la sesion
 * almacena la informacion del usuario y su carrito
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
