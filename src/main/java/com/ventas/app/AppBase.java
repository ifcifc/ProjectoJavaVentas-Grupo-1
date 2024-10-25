package com.ventas.app;

import com.ventas.data.DataBase;
import com.ventas.models.BaseModel;
import com.ventas.services.IService;

import java.util.HashMap;

public abstract class AppBase implements Runnable{
    //protected final DataBase db;

    private final HashMap<Class<?>, IService<? extends BaseModel>> services;
    
    protected AppBase() {
      //  this.db = new DataBase();

        services = new HashMap<>();
    }
    /*
    public DataBase getDb() {
        return db;
    }*/
    
    public <T extends IService<? extends BaseModel>> T getService(Class<T> type) {
        return type.cast(this.services.get(type));
    }

    public void addService(IService<? extends BaseModel> service){
        this.services.put(service.getClass(), service);
    }
}
