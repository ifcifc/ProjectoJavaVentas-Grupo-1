package com.ventas.comparators;

import com.ventas.models.ArticuloModel;
import java.util.Comparator;

public class ComparatorArticulo implements Comparator<ArticuloModel>{
    @Override
    public int compare(ArticuloModel o1, ArticuloModel o2) {
        return o1.getNombre().compareTo(o2.getNombre());
    }

}
