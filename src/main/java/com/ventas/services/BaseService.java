package com.ventas.services;

import java.util.List;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import com.ventas.models.BaseModel;

public class BaseService <T extends BaseModel> implements IService<T>{ 
	protected HashSet<T> data;
	
	public BaseService() {
		this.data = new HashSet<>();
	}

	@Override
	public List<T> getAll() {
		return this.data.stream().toList();//.filter(x->!x.isDelete());
	}

	@Override
	public T getById(UUID id) {
		return this.data.stream()
                            .filter(x->x.getID().equals(id))
                            .findFirst()
                            .orElse(null);
	}

	@Override
	public boolean delete(UUID id) {
		if(!this.any(id))return false;
		
		this.data.removeIf(x->x.getID().equals(id));
		
		return true;
	}

	@Override
	//posiblemente inutil, ya que si se obtiene un modelo se deberia de obtener la instancia del modelo
	public boolean update(T model) {
		if(!this.delete(model.getID()))return false;
		
		this.data.add(model);
		
		return true;
	}

	@Override
	public boolean insert(T model) {
		if(this.any(model.getID()))return false;
		
		this.data.add(model);
		
		return false;
	}
	
	@Override
	public boolean any(UUID id) {
		return this.data.stream().anyMatch(x->x.getID().equals(id));
	}
	
	

}
