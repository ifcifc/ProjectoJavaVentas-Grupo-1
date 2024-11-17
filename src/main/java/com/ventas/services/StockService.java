/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.services;

import com.ventas.models.StockModel;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 *
 * @author igna
 */
public class StockService extends BaseService<StockModel>{
    public HashMap<UUID, StockModel> toArticuloMap(){
        HashMap<UUID, StockModel> map = new HashMap<>();
        
        this.getAll().forEach(x->map.put(x.getArticulo().getID(), x));
        
        return map;
    }
}
