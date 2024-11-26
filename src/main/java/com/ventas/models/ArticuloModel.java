package com.ventas.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ArticuloModel extends BaseModel implements Comparable<ArticuloModel>{
    private long cod;
    private String nombre;//, descripcion;
    //private double precio;
    
    private final ArrayList<HistoryModel<Integer>> stock;
    private final ArrayList<HistoryModel<Double>> precio;
    private final ArrayList<HistoryModel<String>> descripcion;
    
    public ArticuloModel() {
        super();
        this.stock = new ArrayList<>();
        this.precio = new ArrayList<>();
        this.descripcion = new ArrayList<>();
        this.setPrecio(.0);
        this.setDescripcion("");
    }

    public ArticuloModel(long cod, String nombre, String descripción, double precio, int stock) {
        super();
        this.cod = cod;
        this.nombre = nombre;
        //this.descripcion = descripción;
        this.precio = new ArrayList<>();
        this.stock = new ArrayList<>();
        this.descripcion = new ArrayList<>();
        this.addStock(stock, "Inicial");
        this.setPrecio(precio);
        this.setDescripcion(descripción);
    }
    
    public ArticuloModel(UUID id, long cod, String nombre, String descripción, double precio, int stock){
        super(id);
        this.cod = cod;
        this.nombre = nombre;
        //this.descripcion = descripción;
        this.precio = new ArrayList<>();
        this.stock = new ArrayList<>();
        this.descripcion = new ArrayList<>();
        this.addStock(stock, "Inicial");
        this.setPrecio(precio);
        this.setDescripcion(descripción);
    }
    
    

    public long getCod() {
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        if(descripcion.size()==0)return "";
        return descripcion.get(descripcion.size()-1).getValue();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.add(new HistoryModel<>(descripcion));
    }

    public double getPrecio() {
        if(precio.size()==0)return 0;
        return precio.get(precio.size()-1).getValue();
    }

    public void setPrecio(double precio) {
        this.precio.add(new HistoryModel<>(precio));
    }

    public int getStock() {
        //return stock;
        return this.stock
            .stream()
            .mapToInt(x->x.getValue())
            .sum();
    }

    public boolean setStock(int stock, Object org) {
        int stk = stock - this.getStock();
        return this.addStock(stk, org);
    }
    
    public boolean addStock(int stock, Object org){
        if(stock==0)return false;
        
        int stockActual = this.getStock();
        
        if((stockActual+stock)<0){
            stock = -stockActual;
        }
        
        this.stock.add(new HistoryModel<>(stock, org));
        
        return true;
    }

    public ArrayList<HistoryModel<Integer>> getStockHistory(){
        return new ArrayList(this.stock);
    }
    public ArrayList<HistoryModel<Double>> getPrecioHistory(){
        return new ArrayList(this.precio);
    }
    public ArrayList<HistoryModel<String>> getDescripcionHistory(){
        return new ArrayList(this.descripcion);
    }
    
    @Override
    public String toString() {
        return "ArticuloModel{id="+ this.getID() + ", cod=" + cod + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", stock=" + stock + '}';
    }

    
    
    @Override
    public int compareTo(ArticuloModel articulo) {
        return this.nombre.compareTo(articulo.nombre);
    }

    
}
