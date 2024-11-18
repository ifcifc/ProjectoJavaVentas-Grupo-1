/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.services;

import com.ventas.models.MovimientoModel;
import com.ventas.models.UsuarioModel;

/**
 *
 * @author igna
 */
public class MovimientoService extends BaseService<MovimientoModel>{
    
    
    public double getSaldo(UsuarioModel usuario){
        //El movimiento es del usuario cuando
        // 1- El origen es null(ingreso) y el destino es el usuario
        // 2- Cuando el origen es el usuario
        return this.data.stream()
            .filter(x->(x.getFrom()==null && x.getTo().equals(usuario)) || x.getFrom().equals(usuario))
            .mapToDouble(x->x.getMonto())
            .sum();
    }
}
