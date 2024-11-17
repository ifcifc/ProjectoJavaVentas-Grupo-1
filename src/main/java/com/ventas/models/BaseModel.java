package com.ventas.models;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

import com.ventas.utils.UUIDUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseModel implements Serializable{
    //private boolean isDelete;
    private final UUID uuid;

    public BaseModel(UUID id) {
        //this.isDelete = false;
        uuid = id;
    }

    public BaseModel() {
        this(UUIDUtils.randomUUID());
    }

    public UUID getID() {
    	return uuid;
    }

    /*public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }*/ 
}
