/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.data;

import com.ventas.app.App;
import com.ventas.models.ArticuloModel;
import com.ventas.models.CarritoModel;
import com.ventas.models.UsuarioModel;
import com.ventas.services.ArticuloService;
import com.ventas.services.CarritoService;
import java.util.ArrayList;

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
        
        ArticuloModel articuloModel = App.getInstance().getService(ArticuloService.class).getAll().get(0);
        this.carrito.insert(new CarritoModel(articuloModel, 3));
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
