
package com.ventas.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;


public class VentaGroupModel extends BaseModel{

    private final ArrayList<VentaModel> group;
    
    public VentaGroupModel(ArrayList<VentaModel> group, UUID id) {
        super(id);
        this.group = group;
    }

    public VentaGroupModel() {
        this.group = new ArrayList<>();
    }

    public VentaGroupModel(ArrayList<VentaModel> group) {
        this.group = group;
    }

    public ArrayList<VentaModel> getGroup() {
        return group;
    }
    
    public UsuarioModel getUsuario(){
        return this.group.stream().findAny().get().getUsuario();
    }
    
    public double getTotal(){
        return group.stream()
                .mapToDouble(x->x.getTotal())
                .sum();
    }

    public LocalDateTime getFecha(){
        return this.group.stream().findAny().get().getFecha();

    }

    @Override
    public String toString() {
        
        String collect = group
                .stream()
                .map(x->x.toString())
                .collect(Collectors.joining(", "));
        
        return "VentaGroupModel{" + "group=[" + collect + "], usuario=" + this.getUsuario() + '}';
    }
    
    
    
}
