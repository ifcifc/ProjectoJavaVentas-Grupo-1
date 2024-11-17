package Comparator;

import com.ventas.models.ArticuloModel;
import java.util.Comparator;

public class ComparatorArticulo implements Comparator<ArticuloModel>{
    @Override
    public int compare(ArticuloModel o1, ArticuloModel o2) {
        return o1.getNombre().compareTo(o2.getNombre());
    }

    public Comparator<? super ArticuloModel> thenComparing() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
