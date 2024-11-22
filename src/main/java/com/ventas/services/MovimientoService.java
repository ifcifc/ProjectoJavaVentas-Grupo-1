/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.services;

import com.ventas.models.MovimientoModel;
import com.ventas.models.UsuarioModel;
import java.util.List;

/**
 *
 * @author igna
 */
public class MovimientoService extends BaseService<MovimientoModel> {

    public List<MovimientoModel> getMovimientos(UsuarioModel usuario) {
        return this.data.stream()
                .filter(x -> (x.getTo().equals(usuario)) || x.getFrom() != null && x.getFrom().equals(usuario))
                .toList();
    }

    public double getSaldo(UsuarioModel usuario) {
        //El movimiento es del usuario cuando
        // 1- El origen es null(ingreso) y el destino es el usuario
        // 2- Cuando el origen es el usuario
        return this.getMovimientos(usuario)
                .stream()
                .mapToDouble(x -> {
                
                    int from = (x.isTransferencia() && x.getFrom().equals(usuario)) ? -1 : 1;
                    
                    if(x.isVenta()) from=-1;
                    
                    return x.getMonto() * from;
                })
                .sum();
    }
}
