package com.ventas.models;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

import com.ventas.utils.UUIDGenerator;

public abstract class BaseModel implements Serializable {
	private boolean isDelete;
    private UUID uuid;

    public BaseModel(UUID id) {
        this.isDelete = false;
        uuid = id;
    }

    public BaseModel() {
        this(UUIDGenerator.randomUUID());
    }

    public UUID getID() {
    	return uuid;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    
    public abstract String toString();
    
    
    
}
