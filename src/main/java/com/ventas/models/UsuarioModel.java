package com.ventas.models;

import java.util.UUID;

public class UsuarioModel extends BaseModel {
    private String nombre, email, password;
    private boolean isEmpleado;
    private boolean isDelete;

    public UsuarioModel() {
    }

    public UsuarioModel(String nombre, String email, String password, boolean isEmpleado) {
        super();
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.isEmpleado = isEmpleado;
    }
    
    
    public UsuarioModel(UUID id, String nombre, String email, String password, boolean isEmpleado) {
        super(id);
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.isEmpleado = isEmpleado;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        if(this.isDelete)return;
        this.isDelete = isDelete;
    }

    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmpleado() {
        return isEmpleado;
    }

    public void setEmpleado(boolean empleado) {
        isEmpleado = empleado;
    }

    @Override
    public String toString() {
        return "UsuarioModel{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isEmpleado=" + isEmpleado +
                '}';
    }
}
