package com.ventas.models;

import java.io.Serializable;
import java.util.UUID;

public abstract class BaseModel implements Serializable {
	private boolean isDelete;
    private UUID uuid;

    public BaseModel(UUID id) {
        this.isDelete = false;
        uuid = id;
    }

    public BaseModel() {
        this(UUID.randomUUID());
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
