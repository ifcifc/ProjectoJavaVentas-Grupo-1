package com.ventas.services;

import com.ventas.comparators.ComparatorArticulo;
import com.ventas.models.ArticuloModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArticuloService extends BaseService<ArticuloModel>{
        
    @Override
    public List<ArticuloModel> getAll() {
        Comparator<ArticuloModel> comparator = new ComparatorArticulo()
                .thenComparing((ArticuloModel t, ArticuloModel t1) -> 
                        Long.compare(t.getCod(), t1.getCod()));
         
        List<ArticuloModel> all = super.getAll();
         
        all.sort(comparator);
        
        return all;
    }
    
    
    public ArticuloModel getByCod(long cod){
        return this.data.stream()
                        .filter(x->x.getCod()==cod)
                        .findFirst()
                        .orElse(null);
    }
    
    private boolean anyByCod(long cod){
        return this.data.stream()
                        .anyMatch(x->x.getCod()==cod);
    }
    
    @Override
    public boolean update(ArticuloModel model) {
        if(this.anyByCod(model.getCod())){
            if(!this.getByCod(model.getCod())
                    .getID()
                    .equals(model.getID()))return false;
        }
        return super.update(model);
    }

    @Override
    public boolean insert(ArticuloModel model) {
        if(this.anyByCod(model.getCod()))return false;
        return super.insert(model);
    }
}
