package com.ventas.comparators;

import com.ventas.models.VentaModel;
import java.util.Comparator;

public class ComparatorVentaService implements Comparator<VentaModel>{
    @Override
    public int compare(VentaModel o1, VentaModel o2) {
        return o1.getFecha().compareTo(o2.getFecha());
    }

}