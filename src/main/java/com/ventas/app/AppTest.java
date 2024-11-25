package com.ventas.app;

import com.ventas.models.*;
import com.ventas.services.*;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class AppTest extends AppBase{

    public AppTest() {
        super();
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
        this.addClientAccess("/saldo", "ingreso");
        this.addClientAccess("/saldo", "transferencia");
    }

    @Override
    public void run() {
        ArticuloService service = this.getService(ArticuloService.class);
        service.insert(new ArticuloModel(0,"Leche", "Vacuna", 16, 20));
        service.insert(new ArticuloModel(1,"Lechuga", "Sospechosa", 10,15));
        service.insert(new ArticuloModel(2,"Arroz", "Pueden artificial", 132,133));
        service.insert(new ArticuloModel(3,"Carne", "de origen chino", 66,10));
        
        ArticuloModel orElse = service.getAll().stream().filter(x->x.getCod()==1).findFirst().get();
        
        //System.out.println(orElse);
       
        
        UsuarioService us = this.getService(UsuarioService.class);
        
        UsuarioModel admin = new UsuarioModel("Juan", "admin@admin.com", "admin", true);
        UsuarioModel cliente = new UsuarioModel("Pedro", "cliente@cliente.com", "cliente", false);
        us.insert(admin);
        us.insert(cliente);
        us.getAll().forEach(System.out::println);
        
        
        CarritoService cs = this.getService(CarritoService.class);
        /*cs.insert(new CarritoModel (orElse, 3));
        CarritoModel carrito = cs.getAll().stream().findFirst().orElse(null);

        VentaService vs = this.getService(VentaService.class);
        vs.insert(new VentaModel (carrito));*/
        
        
        MovimientoService ms = this.getService(MovimientoService.class);
        ms.insert(new MovimientoModel(116.3, LocalDateTime.now(), null, admin));
        ms.insert(new MovimientoModel(50.63, LocalDateTime.now(), admin, cliente));
        
        
        VentaService vs = this.getService(VentaService.class);
        ArrayList<VentaModel> ventas = new ArrayList();
        
        ventas.add(new VentaModel(cliente, orElse, 33));
        ventas.add(new VentaModel(cliente, orElse, 22));
        ventas.add(new VentaModel(cliente, orElse, 66));

        ventas.forEach(x->{
            vs.insert(x);
        });
        
        VentaGroupModel ventaGroupModel = new VentaGroupModel(ventas);

        VentaGroupService vgs = this.getService(VentaGroupService.class);
        
        vgs.insert(ventaGroupModel);
        
        /*service.getAll().forEach(System.out::println);*/
        /*
        UsuarioService us = new UsuarioService();
        System.out.println(us.getAll());
        System.out.println(us.getById(1));
        ArticuloService as = new ArticuloService();
        System.out.println(as.getAll());
        System.out.println(as.delete(2));
        System.out.println(as.getAll());
        System.out.println(as.getById(3));
        System.out.println("-------------");
        StockService ss = new StockService();
        //ss.insert(new StockModel(1, 3));
        //ss.insert(new StockModel(2, 3));
        System.out.println(ss.getAll());
        System.out.println(ss.delete(3));
        System.out.println(ss.getAll());
        System.out.println(ss.getById(3));
        System.out.println(ss.getById(2));
        System.out.println("-------------");
        CarritoService cs = new CarritoService();
        cs.insert(new CarritoModel(1, 3, false));
        cs.insert(new CarritoModel(1, 3, false));
        cs.insert(new CarritoModel(1, 3, false));
        cs.insert(new CarritoModel(1, 30, false));
        //ss.insert(new StockModel(2, 3));
        System.out.println(cs.getAll());
        System.out.println(cs.delete(3));
        System.out.println(cs.getAll());
        System.out.println(cs.getById(3));
        System.out.println(cs.getById(2));
        CarritoModel byId = cs.getById(2);
        byId.setComprado(true);
        cs.update(byId);
        System.out.println(cs.getById(2));*/
        //this.addService(new VentaService());
        //VentaService vs = this.getService(VentaService.class);
        /*vs.insert(new VentaModel(1,1,"24/04/2024"));
        vs.insert(new VentaModel(2,1,"24/04/2024"));
        vs.insert(new VentaModel(3,1,"24/04/2024"));
        vs.insert(new VentaModel(4,1,"24/04/2024"));*/
        /*vs.getAll().forEach(System.out::println);
        System.out.println("----------------");
        System.out.println(vs.delete(2));
        System.out.println("----------------");
        VentaModel byId = vs.getById(1);
        System.out.println(byId);
        System.out.println("----------------");
        byId.setFecha("asdasd");
        System.out.println(vs.update(byId));
        System.out.println("----------------");
        vs.getAll().forEach(System.out::println);
        System.out.println("----------------");
        System.out.println(vs.getById(1));
        System.out.println("----------------");*/
        /*MovimientoService ms = this.getService(MovimientoService.class);
        ms.insert(new MovimientoModel(1,0,0,0, ""));
        ms.insert(new MovimientoModel(25,0,0,0, ""));
        ms.insert(new MovimientoModel(3,0,0,0, ""));
        Optional<MovimientoModel> max = ms.getAll().stream().max(Comparator.comparingInt(MovimientoModel::getID));
        System.out.println(max.get());
        ms.getAll().forEach(System.out::println);
        System.out.println("----------------");
        System.out.println(ms.delete(2));;
        System.out.println("----------------");
        ms.getAll().forEach(System.out::println);
        System.out.println("----------------");
        MovimientoModel byId = ms.getById(3);
        System.out.println(byId);
        System.out.println("----------------");
        byId.setFecha("algo");
        System.out.println(ms.update(byId));
        System.out.println("----------------");
        ms.getAll().forEach(System.out::println);
        System.out.println("----------------");*/

    } 
}
