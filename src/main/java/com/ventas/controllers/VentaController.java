package com.ventas.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ventas.app.App;
import com.ventas.data.SecureOptional;
import com.ventas.data.SessionDecorator;
import com.ventas.models.MovimientoModel;
import com.ventas.models.UsuarioModel;
import com.ventas.models.VentaGroupModel;
import com.ventas.models.VentaModel;
import com.ventas.services.ArticuloService;
import com.ventas.services.CarritoService;
import com.ventas.services.MovimientoService;
import com.ventas.services.UsuarioService;
import com.ventas.services.VentaGroupService;
import com.ventas.services.VentaService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class CarritoController
 */
@WebServlet("/ventas")
public class VentaController extends BaseController {

    private static final long serialVersionUID = 1L;
    private final VentaService ventaService;
    private final UsuarioService usuarioService;
    private final MovimientoService movimientoService;
    private final VentaGroupService ventaGroupService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public VentaController() {
        super();
        this.ventaService = App.getInstance()
                .getService(VentaService.class);

        this.usuarioService = App.getInstance()
                .getService(UsuarioService.class);
        App.getInstance().getService(ArticuloService.class);
        this.movimientoService = App.getInstance().getService(MovimientoService.class);
        this.ventaGroupService = App.getInstance().getService(VentaGroupService.class);

    }

    public void getShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        //String idg = request.getParameter("idg");
        if (id == null) {// || idg == null
            getIndex(request, response);
            return;
        }

        var venta = this.ventaService.getById(id);
        if (venta == null) {
            this.showMessage(request, response, "Hubo un problema", "No se a encontrado la venta", "javascript:window.history.back()");

            return;
        }
        
        request.setAttribute("venta", venta);
        //request.setAttribute("idg", idg);
        request.getRequestDispatcher("/views/venta/show.jsp").forward(request, response);
    }

    /*public void getEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var venta = this.ventaService.getById(id);
        if (venta == null) {
            this.showMessage(request, response, "Hubo un problema", "No se a encontrado la venta", "javascript:window.history.back()");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"); 
        String formattedDateTime = venta.getFecha().format(formatter);
        
        
        request.setAttribute("method", "POST");
        request.setAttribute("action", "");
        request.setAttribute("venta", venta);
        request.setAttribute("venta_fecha", formattedDateTime);
        request.setAttribute("articulos", this.articuloService.getAll());
        request.setAttribute("usuarios", this.usuarioService.getAll());

        request.getRequestDispatcher("/views/venta/addedit.jsp").forward(request, response);
    }

    public void getDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var venta = this.ventaService.getById(id);
        if (venta == null) {
            this.showMessage(request, response, "Hubo un problema", "No se a encontrado la venta", "javascript:window.history.back()");
            return;
        }

        request.setAttribute("venta", venta);
        request.getRequestDispatcher("/views/venta/delete.jsp").forward(request, response);
    }

    public void postDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        boolean result = this.ventaService.delete(id);
        if (result) {
            this.showMessage(request, response, "Stock", "Se a eliminado la venta correctamente", "ventas");
        } else {
            this.showMessage(request, response, "Ah ocurrido un problema", "Ah habido un problema al eliminar la venta", "ventas");
        }
    }*/
    @Override
    void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<VentaGroupModel> all = this.ventaGroupService.getAll();
        LocalDateTime fecha_from = SecureOptional
                .ofNullable(request.getParameter("fecha_from"))
                .secureMap(x-> LocalDate.parse(x, DateTimeFormatter.ISO_DATE).atStartOfDay())
                .orElse(LocalDateTime.now().minusDays(30));

        LocalDateTime fecha_to = SecureOptional
                .ofNullable(request.getParameter("fecha_to"))
                .secureMap(x-> LocalDate.parse(x, DateTimeFormatter.ISO_DATE).atStartOfDay())
                .orElse(LocalDateTime.now().plusDays(1));
        
        all.removeIf(x->{
            LocalDateTime fecha = x.getFecha();
        
            return !((fecha.isAfter(fecha_from) || fecha.isEqual(fecha_from)) 
                   &&(fecha.isBefore(fecha_to) || fecha.isEqual(fecha_to)));
        });
        
        
        request.setAttribute("fecha_from", fecha_from.format(DateTimeFormatter.ISO_DATE));
        request.setAttribute("fecha_to", fecha_to.format(DateTimeFormatter.ISO_DATE));

        request.setAttribute("ventasGroup", all);
        request.getRequestDispatcher("/views/venta/index.jsp").forward(request, response);
    }

    public void getView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String last = request.getParameter("last");
        if (id == null) {
            getIndex(request, response);
            return;
        }

        last = (last.equals("ventas")) ? "/ventas" : (last.equals("all")? "/saldo?accion=all" : "/saldo");
        
        
        //request.setAttribute("idg", id);
        request.setAttribute("last", request.getContextPath()+last);
        request.setAttribute("ventas", this.ventaGroupService.getById(id).getGroup());
        request.getRequestDispatcher("/views/venta/ventaView.jsp").forward(request, response);
    }

    /*public void postEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID id_articulo = UUID.fromString(request.getParameter("articulo"));
        UUID id_usuario = UUID.fromString(request.getParameter("usuario"));
        
        ArticuloModel articulo = this.articuloService.getById(id_articulo);
        UsuarioModel usuario = this.usuarioService.getById(id_usuario);
        
        boolean result = this.ventaService.update(new VentaModel(
                UUID.fromString(request.getParameter("id")),
                usuario,
                articulo,
                LocalDateTime.parse(request.getParameter("fecha")),
                Integer.parseInt(request.getParameter("cantidad"))
        ));

        if (result) {
            this.showMessage(request, response, "Stock", "Se a modificado la venta correctamente", "ventas");
        } else {
            this.showMessage(request, response, "Ah ocurrido un problema", "Ah habido un problema al modificar la venta", "ventas");
        }
    }

    public void getCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"); 
        String formattedDateTime = LocalDateTime.now().format(formatter);
        
        
        request.setAttribute("method", "POST");
        request.setAttribute("action", "");
        request.setAttribute("venta", new VentaModel());
        request.setAttribute("venta_fecha", formattedDateTime);
        request.setAttribute("articulos", this.articuloService.getAll());
        request.setAttribute("usuarios", this.usuarioService.getAll());

        request.getRequestDispatcher("/views/venta/addedit.jsp").forward(request, response);
    }

    public void postCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID id_articulo = UUID.fromString(request.getParameter("articulo"));
        UUID id_usuario = UUID.fromString(request.getParameter("usuario"));
        
        ArticuloModel articulo = this.articuloService.getById(id_articulo);
        UsuarioModel usuario = this.usuarioService.getById(id_usuario);
        
        boolean result = this.ventaService.insert(new VentaModel(
                usuario,
                articulo,
                LocalDateTime.parse(request.getParameter("fecha")),
                Integer.parseInt(request.getParameter("cantidad"))
        ));

        if (result) {
            this.showMessage(request, response, "Stock", "Se a cargado la venta correctamente", "ventas");
        } else {
            this.showMessage(request, response, "Ah ocurrido un problema", "Ah habido un problema al crear la venta", "ventas");
        }
    }*/
    public void postVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        CarritoService carrito = sessionDecorator.getCarrito();
        UsuarioModel usuario = sessionDecorator.getUsuario();

        double total = carrito.getTotal();
        double saldo = this.movimientoService.getSaldo(sessionDecorator.getUsuario());

        if (total > saldo) {
            this.showMessage(request, response, "Hubo un problema", "Usted no posee suficiente saldo para realizar la compra", "carrito?accion=client");
            return;
        }

        boolean carritoOK = carrito.getAll().stream().anyMatch(x -> x.getCantidad() > x.getArticulo().getStock());

        if (carritoOK) {
            this.showMessage(request, response, "Hubo un problema", "No hay suficientes stock de uno o mas articulos para realizar la compra", "carrito?accion=carrito");
            return;
        }

        //Mucho trabajo para implementar una especie de transaccion para algo tan sencillo
        //En el improbable caso de que hayan dos o mas ventas al mismo tiempo podria ocurrir que justo se venda un mismo articulo 2 o mas veces
        ArrayList<VentaModel> ventaList = new ArrayList();

        carrito.getAll().forEach(x -> {
            VentaModel venta = new VentaModel(usuario, x);
            ventaList.add(venta);
            var articulo = x.getArticulo();
            this.ventaService.insert(venta);
            articulo.addStock(-x.getCantidad(), venta);
            carrito.delete(x.getID());
        });

        VentaGroupModel venta = new VentaGroupModel(ventaList);
        MovimientoModel movimiento = new MovimientoModel(venta.getTotal(), venta, usuario);
        this.movimientoService.insert(movimiento);
        this.ventaGroupService.insert(venta);

        this.showMessage(request, response, "Venta completada", "La compra fue completada correctamente", "articulos?accion=client");
    }

}
