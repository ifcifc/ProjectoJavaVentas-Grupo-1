package com.ventas.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ventas.app.App;
import com.ventas.data.SessionDecorator;
import com.ventas.models.ArticuloModel;
import com.ventas.services.ArticuloService;
import com.ventas.services.CarritoService;
import com.ventas.services.MovimientoService;
import com.ventas.utils.UUIDUtils;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/articulos")
public class ArticulosController extends BaseController {

    private static final long serialVersionUID = 1L;
    private final ArticuloService articuloService;
    private final CarritoService carritoService;
    private final MovimientoService movimientoService;

    public ArticulosController() {
        super();
        this.articuloService = App.getInstance()
                .getService(ArticuloService.class);
        this.carritoService = App.getInstance()
                .getService(CarritoService.class);
        this.movimientoService = App.getInstance()
                .getService(MovimientoService.class);

    }

    @Override
    public void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ArticuloModel> all = this.articuloService.getAll();

        request.setAttribute("articulos", all);

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

        request.getRequestDispatcher("/views/articulo/addedit.jsp").forward(request, response);
    }

    public void getStock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        request.getRequestDispatcher("/views/articulo/stock.jsp").forward(request, response);
    }

    public void getPrecio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        request.getRequestDispatcher("/views/articulo/precio.jsp").forward(request, response);
    }

    public void postPrecio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        double precio = Optional
                .ofNullable(request.getParameter("precio"))
                .map(x -> Double.valueOf(x))
                .orElse(0.0);

        articulo.setPrecio(precio);
        this.articuloService.update(articulo);

        this.showMessage(request, response, "Articulo", "Se a modificado el stock correctamente", "articulos");
    }

    public void postStock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        int stock = Optional
                .ofNullable(request.getParameter("stock"))
                .map(x -> Integer.parseInt(x))
                .orElse(0);

        articulo.setStock(stock);
        this.articuloService.update(articulo);

        this.showMessage(request, response, "Articulo", "Se a modificado el stock correctamente", "articulos");
    }

    public void postCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long code = Optional
                .ofNullable(request.getParameter("cod"))
                .map(x -> Long.valueOf(x))
                .orElse(0L);

        String nombre = Optional
                .ofNullable(request.getParameter("nombre"))
                .orElse("Desconocido");

        String descripcion = Optional
                .ofNullable(request.getParameter("descripcion"))
                .orElse("");

        boolean anyMatch = this.articuloService.getAll()
                .stream()
                .anyMatch(
                        x -> x.getCod() == code
                        || x.getNombre().equals(nombre) && x.getDescripcion().equals(descripcion));

        if (anyMatch) {
            this.showMessage(request, response, "Articulo", "Ya existe este articulo", "articulos");
            return;
        }

        double precio = Optional
                .ofNullable(request.getParameter("precio"))
                .map(x -> Double.valueOf(x))
                .orElse(0.0);

        int stock = Optional
                .ofNullable(request.getParameter("stock"))
                .map(x -> Integer.valueOf(x))
                .orElse(0);

        boolean result = this.articuloService.insert(new ArticuloModel(
                code,
                nombre,
                descripcion,
                precio,
                stock
        ));

        if (result) {
            this.showMessage(request, response, "Articulo", "Se a cargado el articulo correctamente", "articulos");
        } else {
            this.showMessage(request, response, "Ah ocurrido un problema", "Ah habido un problema al crear el articulo", "articulos");
        }
    }

    public void postEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long code = Optional
                .ofNullable(request.getParameter("cod"))
                .map(x -> Long.valueOf(x))
                .orElse(0L);

        String nombre = Optional
                .ofNullable(request.getParameter("nombre"))
                .orElse("Desconocido");

        String descripcion = Optional
                .ofNullable(request.getParameter("descripcion"))
                .orElse("");
        
        UUID uid = UUIDUtils.fromString(request.getParameter("id"));

        ArticuloModel byId = this.articuloService.getById(uid);
        
        boolean anyMatch = this.articuloService.getAll()
                .stream()
                .anyMatch(x -> !x.equals(byId) &&
                        (x.getCod() == code
                        || x.getNombre().equals(nombre) && x.getDescripcion().equals(descripcion)));

        if (anyMatch) {
            this.showMessage(request, response, "Articulo", "Ya existe este articulo", "articulos");
            return;
        }

        double precio = Optional
                .ofNullable(request.getParameter("precio"))
                .map(x -> Double.valueOf(x))
                .orElse(0.0);

        int stock = Optional
                .ofNullable(request.getParameter("stock"))
                .map(x -> Integer.valueOf(x))
                .orElse(0);

        boolean result = this.articuloService.update(new ArticuloModel(
                UUIDUtils.fromString(request.getParameter("id")),
                code,
                nombre,
                descripcion,
                precio,
                stock
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

        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        double saldo = this.movimientoService.getSaldo(sessionDecorator.getUsuario());
        request.setAttribute("saldo", saldo);
        request.setAttribute("carrito", sessionDecorator.getCarrito().toArticuloMap());
        request.setAttribute("articulos", all);

        request.getRequestDispatcher("/views/articulo/clientView.jsp").forward(request, response);
    }

}
