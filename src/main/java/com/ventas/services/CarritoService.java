/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.services;

import com.ventas.comparators.ComparatorCarrito;
import com.ventas.models.ArticuloModel;
import com.ventas.models.CarritoModel;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CarritoService extends BaseService<CarritoModel>{
	
    @Override
    public List<CarritoModel> getAll() {
        Comparator<CarritoModel> comparator = new ComparatorCarrito()
        		.thenComparing((CarritoModel o1, CarritoModel o2) ->
        				Integer.compare(o1.getCantidad(), o2.getCantidad())	);
         
        List<CarritoModel> all = super.getAll();
         
        all.sort(comparator);
        
        return all;
    }
    
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
