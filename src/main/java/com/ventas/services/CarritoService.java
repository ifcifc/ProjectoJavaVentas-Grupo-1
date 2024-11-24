/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.services;

import com.ventas.models.CarritoModel;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author igna
 */
public class CarritoService extends BaseService<CarritoModel>{
    public Map<UUID, CarritoModel> toArticuloMap(){
        HashMap<UUID, CarritoModel> map = new HashMap<>();
        
        this.getAll().forEach(x->map.put(x.getArticulo().getID(), x));
        
        return map;
    }
    
    public double getTotal(){
        return this.data
                .stream()
                .mapToDouble(x->x.getCantidad()*x.getArticulo().getPrecio())
                .sum();
    }
}
