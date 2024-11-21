/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.app;

import com.ventas.models.UsuarioModel;
import com.ventas.services.ArticuloService;
import com.ventas.services.MovimientoService;
import com.ventas.services.UsuarioService;
import com.ventas.services.VentaService;

/**
 *
 * @author igna
 */
public class AppVentas extends AppBase{

    public AppVentas() {
        this.addService(new ArticuloService());
        this.addService(new UsuarioService());
        this.addService(new MovimientoService());
        this.addService(new VentaService());
    }

    @Override
    public void run() {
        UsuarioService service = this.getService(UsuarioService.class);
        //Usuario administrador por defecto
        service.insert(new UsuarioModel("Admin", "admin@admin.com", "admin", true));
    }

}
