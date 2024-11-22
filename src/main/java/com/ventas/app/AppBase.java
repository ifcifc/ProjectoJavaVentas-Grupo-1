package com.ventas.app;

import com.ventas.models.BaseModel;
import com.ventas.services.IService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

public abstract class AppBase implements Runnable{
    //protected final DataBase db;

    private final HashMap<Class<?>, IService<? extends BaseModel>> services;
    private final HashMap<String, HashSet<String>> clientPermission;
    
    protected AppBase() {
      //  this.db = new DataBase();
        this.clientPermission = new HashMap<>();
        this.services = new HashMap<>();
    }
    /*
    public DataBase getDb() {
        return db;
    }*/
    
    public <T extends IService<? extends BaseModel>> T getService(Class<T> type) {
        return type.cast(this.services.get(type));
    }

    protected void addService(IService<? extends BaseModel> service){
        this.services.put(service.getClass(), service);
    }
    
    
    
    /**
     * Estos metodos sirven para verificar si un cliente puede acceder a una parte de la pagina
     * El cliente solo podra acceder a un controlador si este esta registrado en clientPermission
     * Y a la accion si esta registrada en el HashSet 
     */
    
    protected void addClientAccess(String controller, String accion){
        if(!this.clientPermission.containsKey(controller))
            this.clientPermission.put(controller, new HashSet<>());
        
        this.clientPermission.get(controller).add(Optional.ofNullable(accion).orElse("index"));
    }

    //Comprueba si el cliente tiene acceso a un controlador y a la accion
    public boolean hasClientAccess(String controller, String accion) {
        if(!this.clientPermission.containsKey(controller))return false;
        
        
        
        return this.clientPermission.get(controller).contains(Optional.ofNullable(accion).orElse("index"));
    }
}
