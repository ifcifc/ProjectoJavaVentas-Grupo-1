package com.ventas.comparators;

import com.ventas.models.UsuarioModel;
import java.util.Comparator;

public class ComparatorUsuario implements Comparator<UsuarioModel>{
    @Override
    public int compare(UsuarioModel o1, UsuarioModel o2) {
        return o1.getNombre().toLowerCase().compareTo(o2.getNombre().toLowerCase());
    }

}