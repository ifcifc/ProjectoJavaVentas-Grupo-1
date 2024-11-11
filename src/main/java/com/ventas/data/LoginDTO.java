/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.data;

import com.ventas.models.ArticuloModel;
import com.ventas.models.CarritoModel;
import com.ventas.models.UsuarioModel;
import java.util.ArrayList;

/**
 *
 * @author igna
 */
public class LoginDTO {
    private UsuarioModel usuario;
    private final ArrayList<CarritoModel> carrito;
     
    public LoginDTO(UsuarioModel usuario) {
        this.usuario = usuario;
        this.carrito = new ArrayList<>();
        
        ArticuloModel articuloModel = new ArticuloModel(0, "Prueba", "Algo", 33);
        this.carrito.add(new CarritoModel(usuario, articuloModel, 3));
    }

    public ArrayList<CarritoModel> getCarrito() {
        return carrito;
    }

    
    
    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
    
    
    
}
