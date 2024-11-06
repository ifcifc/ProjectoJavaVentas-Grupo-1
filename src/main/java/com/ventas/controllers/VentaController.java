package com.ventas.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ventas.app.App;
import com.ventas.models.ArticuloModel;
import com.ventas.models.StockModel;
import com.ventas.models.UsuarioModel;
import com.ventas.models.VentaModel;
import com.ventas.services.ArticuloService;
import com.ventas.services.UsuarioService;
import com.ventas.services.VentaService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Servlet implementation class CarritoController
 */
@WebServlet("/ventas")
public class VentaController extends BaseController {

    private static final long serialVersionUID = 1L;
    private final VentaService ventaService;
    private final ArticuloService articuloService;
    private final UsuarioService usuarioService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VentaController() {
        super();
        this.ventaService = App.getInstance()
                .getService(VentaService.class);
        
        this.usuarioService = App.getInstance()
				  .getService(UsuarioService.class);
        this.articuloService = App.getInstance().getService(ArticuloService.class);
    }

    public void getShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var venta = this.ventaService.getById(id);
        if (venta == null) {
            response.sendError(404, "No se a encontrado la venta");
            return;
        }

        request.setAttribute("venta", venta);
        request.getRequestDispatcher("/views/venta/show.jsp").forward(request, response);
    }

    public void getEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var venta = this.ventaService.getById(id);
        if (venta == null) {
            response.sendError(404, "No se a encontrado la venta");
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
            response.sendError(404, "No se a encontrado la venta");
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
    }

    @Override
    void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("ventas", this.ventaService.getAll());
        request.getRequestDispatcher("/views/venta/index.jsp").forward(request, response);
    }

    public void postEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    }

}
