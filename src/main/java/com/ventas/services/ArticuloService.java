package com.ventas.services;

import com.ventas.models.ArticuloModel;

public class ArticuloService extends BaseService<ArticuloModel>{
    
    
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
