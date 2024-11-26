package com.ventas.comparators;

import com.ventas.models.VentaGroupModel;
import java.util.Comparator;

public class ComparatorVentaGroup  implements Comparator<VentaGroupModel>{
    @Override
    public int compare(VentaGroupModel o1, VentaGroupModel o2) {
        return o1.getFecha().compareTo(o2.getFecha());
    }

}
