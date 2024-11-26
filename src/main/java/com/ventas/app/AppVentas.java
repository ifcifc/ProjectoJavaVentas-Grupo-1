/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.app;

import com.ventas.models.ArticuloModel;
import com.ventas.models.UsuarioModel;
import com.ventas.services.ArticuloService;
import com.ventas.services.CarritoService;
import com.ventas.services.MovimientoService;
import com.ventas.services.UsuarioService;
import com.ventas.services.VentaGroupService;
import com.ventas.services.VentaService;

/**
 *
 * @author igna
 */
public class AppVentas extends AppBase{

    public AppVentas() {
        this.addService(new ArticuloService());
        this.addService(new UsuarioService());
        this.addService(new CarritoService());
        this.addService(new MovimientoService());
        this.addService(new VentaService());
        this.addService(new VentaGroupService());
        
        this.addClientAccess("/", "index");
        this.addClientAccess("/auth", "index");
        this.addClientAccess("/auth", "login");
        this.addClientAccess("/auth", "logout");
        this.addClientAccess("/auth", "registro");
        this.addClientAccess("/articulos", "client");
        this.addClientAccess("/carrito", "carrito");
        this.addClientAccess("/ventas", "venta");
        this.addClientAccess("/ventas", "view");
        this.addClientAccess("/ventas", "show");
        this.addClientAccess("/saldo", "index");
        this.addClientAccess("/saldo", "show");
        this.addClientAccess("/saldo", "ingreso");
        this.addClientAccess("/saldo", "transferencia");
    }

    @Override
    public void run() {
        UsuarioService us = this.getService(UsuarioService.class);
        //Usuario administrador por defecto
        us.insert(new UsuarioModel("Admin", "admin@admin.com", "admin", true));
        us.insert(new UsuarioModel("Pedro", "cliente@cliente.com", "cliente", false));
        
        ArticuloService as = this.getService(ArticuloService.class);
        
        as.insert(new ArticuloModel(1, "Leche", "Leche entera de vaca, 1 litro", 16.50, 20));
        as.insert(new ArticuloModel(2, "Pan", "Barra de pan blanco recién horneado", 8.90, 0));
        //as.insert(new ArticuloModel(3, "Huevos", "Cartón de 12 huevos grandes", 45.00, 30));
        //as.insert(new ArticuloModel(4, "Arroz", "Arroz grano largo, paquete 1kg", 28.50, 40));
        as.insert(new ArticuloModel(5, "Aceite", "Aceite de oliva extra virgen 1L", 89.90, 5));
        //as.insert(new ArticuloModel(6, "Azúcar", "Azúcar blanca refinada 1kg", 25.00, 35));
        //as.insert(new ArticuloModel(7, "Café", "Café molido premium 500g", 120.00, 25));
        as.insert(new ArticuloModel(8, "Jabón", "Jabón de tocador pack 3 unidades", 35.50, 10));
        as.insert(new ArticuloModel(9, "Pasta", "Spaghetti 500g", 18.90, 60));
        //as.insert(new ArticuloModel(10, "Atún", "Lata de atún en agua 170g", 22.50, 40));
    }

}
