package com.ventas.services;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import com.ventas.models.BaseModel;
import com.ventas.utils.ModelUpdate;
import java.util.ArrayList;

public class BaseService<T extends BaseModel> implements IService<T> {

    protected HashSet<T> data;

    public BaseService() {
        this.data = new HashSet<>();
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(this.data.stream().toList());//.filter(x->!x.isDelete());
    }

    @Override
    public T getById(String id) {
        try {
            return this.getById(UUID.fromString(id));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public T getById(UUID id) {
        return this.data.stream()
                .filter(x -> x.getID().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean delete(UUID id) {
        if (!this.any(id)) {
            return false;
        }

        this.data.removeIf(x -> x.getID().equals(id));

        return true;
    }

    @Override
    public boolean delete(String id) {
        try {
            return this.delete(UUID.fromString(id));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(T model) {
        /*if (!this.delete(model.getID())) {
        return false;
        }
        return this.data.add(model);*/
        T byId = this.getById(model.getID());
       
        if(byId==null)return false;
        
        try {
            ModelUpdate.update(model, byId);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean insert(T model) {
        if (this.any(model.getID())) {
            return false;
        }
        return this.data.add(model);
    }

    @Override
    public boolean any(UUID id) {
        return this.data.stream().anyMatch(x -> x.getID().equals(id));
    }

}
