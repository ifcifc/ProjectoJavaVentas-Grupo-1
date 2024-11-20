package com.ventas.controllers;

import Comparator.ComparatorArticulo;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ventas.app.App;
import com.ventas.data.SessionDecorator;
import com.ventas.models.ArticuloModel;
import com.ventas.models.UsuarioModel;
import com.ventas.services.ArticuloService;
import com.ventas.services.CarritoService;
import com.ventas.services.MovimientoService;
import com.ventas.services.StockService;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@WebServlet("/articulos")
public class ArticulosController extends BaseController {

    private static final long serialVersionUID = 1L;
    private final ArticuloService articuloService;
    private final CarritoService carritoService;
    private final StockService stockService;
    private final MovimientoService movimientoService;
    private final Comparator comparator;

    public ArticulosController() {
        super();
        this.articuloService = App.getInstance()
                .getService(ArticuloService.class);
        this.carritoService = App.getInstance()
                .getService(CarritoService.class);
        this.stockService = App.getInstance()
                .getService(StockService.class);
        this.movimientoService = App.getInstance()
                .getService(MovimientoService.class);

        this.comparator = new ComparatorArticulo()
                .thenComparing(
                        (ArticuloModel t, ArticuloModel t1) -> Long.compare(t.getCod(), t1.getCod()));
    }

    @Override
    public void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ArticuloModel> all = this.articuloService.getAll();
        all.sort(this.comparator);
        
        request.setAttribute("articulos", all);
        
        request.setAttribute("stock", this.stockService.toArticuloMap());
        request.getRequestDispatcher("/views/articulo/index.jsp").forward(request, response);
    }

    public void getShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var articulo = this.articuloService.getById(id);
        if (articulo == null) {
            response.sendError(404, "No se a encontrado el articulo");
            return;
        }

        request.setAttribute("articulo", articulo);
        request.getRequestDispatcher("/views/articulo/show.jsp").forward(request, response);
    }

    public void getEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var articulo = this.articuloService.getById(id);
        if (articulo == null) {
            response.sendError(404, "No se a encontrado el articulo");
            return;
        }

        request.setAttribute("method", "POST");
        request.setAttribute("action", "");
        request.setAttribute("articulo", articulo);

        request.getRequestDispatcher("/views/articulo/addedit.jsp").forward(request, response);
    }

    public void getDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var articulo = this.articuloService.getById(id);
        if (articulo == null) {
            response.sendError(404, "No se a encontrado el articulo");
            return;
        }

        request.setAttribute("articulo", articulo);
        request.getRequestDispatcher("/views/articulo/delete.jsp").forward(request, response);
    }

    public void getCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("articulo", new ArticuloModel());
        request.setAttribute("method", "POST");
        request.setAttribute("action", "");

        request.getRequestDispatcher("/views/articulo/addedit.jsp").forward(request, response);
    }

    public void postCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean result = this.articuloService.insert(new ArticuloModel(
                Long.parseLong(request.getParameter("cod")),
                request.getParameter("nombre"),
                request.getParameter("descripcion"),
                Double.parseDouble(request.getParameter("precio"))
        ));

        if (result) {
            this.showMessage(request, response, "Articulo", "Se a cargado el articulo correctamente", "articulos");
        } else {
            this.showMessage(request, response, "Ah ocurrido un problema", "Ah habido un problema al crear el articulo", "articulos");
        }
    }

    public void postEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean result = this.articuloService.update(new ArticuloModel(
                UUID.fromString(request.getParameter("id")),
                Long.parseLong(request.getParameter("cod")),
                request.getParameter("nombre"),
                request.getParameter("descripcion"),
                Double.parseDouble(request.getParameter("precio"))
        ));

        if (result) {
            this.showMessage(request, response, "Articulo", "Se a modificado el articulo correctamente", "articulos");
        } else {
            this.showMessage(request, response, "Ah ocurrido un problema", "Ah habido un problema al modificar el articulo", "articulos");
        }
    }

    public void postDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        boolean result = this.articuloService.delete(id);
        if (result) {
            this.showMessage(request, response, "Articulo", "Se a eliminado el articulo correctamente", "articulos");
        } else {
            this.showMessage(request, response, "Ah ocurrido un problema", "Ah habido un problema al eliminar el articulo", "articulos");
        }
    }

    public void getClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ArticuloModel> all = this.articuloService.getAll();
        all.sort(this.comparator);

        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        double saldo = this.movimientoService.getSaldo(sessionDecorator.getUsuario());
        request.setAttribute("saldo", saldo);
        //Convierto la lista de carrito y stock en un diccionario para simplificar la logica del jsp
        request.setAttribute("carrito", sessionDecorator.getCarrito().toArticuloMap());
        request.setAttribute("stock", this.stockService.toArticuloMap());
        request.setAttribute("articulos", all);

        request.getRequestDispatcher("/views/articulo/clientView.jsp").forward(request, response);
    }



}
