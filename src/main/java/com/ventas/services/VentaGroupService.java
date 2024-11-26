package com.ventas.services;

import java.util.Comparator;
import com.ventas.comparators.ComparatorVentaGroup;
import java.util.List;

import com.ventas.models.MovimientoModel;
import com.ventas.models.VentaGroupModel;



public class VentaGroupService extends BaseService<VentaGroupModel>{
    
    @Override
    public List<VentaGroupModel> getAll() {
        Comparator<VentaGroupModel> comparator = new ComparatorVentaGroup().reversed();
         
        List<VentaGroupModel> all = super.getAll();
         
        all.sort(comparator);
        
        return all;
    }
}
