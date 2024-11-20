/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.services;

import com.ventas.models.ArticuloModel;
import com.ventas.models.CarritoModel;
import com.ventas.models.StockModel;
import com.ventas.models.UsuarioModel;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    
    public double getTotal(ArticuloModel... ignore){
        Stream<ArticuloModel> ignoreList = Stream.of(ignore);
        return this.data
                .stream()
                .filter(x-> !ignoreList.anyMatch(a->a.equals(x.getArticulo())))
                .mapToDouble(x->x.getCantidad()*x.getArticulo().getPrecio())
                .sum();
    }
}
