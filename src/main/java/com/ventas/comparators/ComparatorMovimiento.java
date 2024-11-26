package com.ventas.comparators;

import com.ventas.models.MovimientoModel;
import java.util.Comparator;

public class ComparatorMovimiento implements Comparator<MovimientoModel>{
    @Override
    public int compare(MovimientoModel o1, MovimientoModel o2) {
        return o1.getFecha().compareTo(o2.getFecha());
    }

}
