/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.services;

import java.util.Comparator;
import java.util.List;

import com.ventas.comparators.ComparatorArticulo;
import com.ventas.comparators.ComparatorVentaService;
import com.ventas.models.ArticuloModel;
import com.ventas.models.UsuarioModel;
import com.ventas.models.VentaModel;


public class VentaService extends BaseService<VentaModel>{
    
    @Override
    public List<VentaModel> getAll() {
        Comparator<VentaModel> comparator = new ComparatorVentaService().reversed()
                .thenComparing((VentaModel o1, VentaModel o2)->
                		Double.compare(o1.getTotal(), o2.getTotal()))
                .thenComparing((VentaModel t, VentaModel t1) -> 
                		t.getArticulo().compareTo(t1.getArticulo()));
         
        List<VentaModel> all = super.getAll();
         
        all.sort(comparator);
        
        return all;
    }
}
