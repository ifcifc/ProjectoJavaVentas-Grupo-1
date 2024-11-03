package com.ventas.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ventas.app.App;
import com.ventas.models.ArticuloModel;
import com.ventas.services.ArticuloService;
import java.util.List;
import java.util.UUID;

@WebServlet("/articulos")
public class ArticulosController extends BaseController {

    private static final long serialVersionUID = 1L;
    private final ArticuloService articuloService;

    public ArticulosController() {
        super();
        this.articuloService = App.getInstance()
                .getService(ArticuloService.class);

    }

    @Override
    public void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ArticuloModel> all = this.articuloService.getAll();
        all.sort((a,b)->a.getNombre().compareTo(b.getNombre()));
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

        request.setAttribute("method", "POST");
        request.setAttribute("action", "");
        request.setAttribute("articulo", articulo);

        request.getRequestDispatcher("/views/articulo/addedit.jsp").forward(request, response);
    }

    public void getDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

}
