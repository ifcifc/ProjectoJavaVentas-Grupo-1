package com.ventas.comparators;

import com.ventas.models.CarritoModel;
import java.util.Comparator;

public class ComparatorCarrito implements Comparator<CarritoModel>{
    @Override
    public int compare(CarritoModel o1, CarritoModel o2) {
        return o1.getArticulo().getNombre().toLowerCase().compareTo(o2.getArticulo().getNombre().toLowerCase());
    }

}