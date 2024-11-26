/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.services;

import com.ventas.comparators.ComparatorMovimiento;
import com.ventas.models.MovimientoModel;
import com.ventas.models.UsuarioModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author igna
 */
public class MovimientoService extends BaseService<MovimientoModel> {

    public List<MovimientoModel> getMovimientos(UsuarioModel usuario) {
    	return new ArrayList<>(this.getAll()
    			.stream()
    			.filter(x -> (x.getTo().equals(usuario)) || x.getFrom() != null && x.getFrom().equals(usuario))
    			.toList());
    }
    
    @Override
    public List<MovimientoModel> getAll() {
        Comparator<MovimientoModel> comparator = new ComparatorMovimiento().reversed()
        		.thenComparing((MovimientoModel o1, MovimientoModel o2) ->
        				Double.compare(o1.getMonto(), o2.getMonto()));
         
        List<MovimientoModel> all = super.getAll();
         
        all.sort(comparator);
        
        return all;
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
