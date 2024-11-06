/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ventas.controllers;

import com.ventas.app.App;
import com.ventas.models.ArticuloModel;
import com.ventas.models.StockModel;
import com.ventas.services.ArticuloService;
import com.ventas.services.StockService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author igna
 */
@WebServlet("/stock")
public class StockController extends BaseController {

    private StockService stockService;
    private ArticuloService articuloService;
    
    public StockController() {
        this.stockService = App.getInstance().getService(StockService.class);
        this.articuloService = App.getInstance().getService(ArticuloService.class);
    }

    public void getShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var stock = this.stockService.getById(id);
        if (stock == null) {
            response.sendError(404, "No se a encontrado el stock");
            return;
        }

        request.setAttribute("stock", stock);
        request.getRequestDispatcher("/views/stock/show.jsp").forward(request, response);
    }

    public void getEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var stock = this.stockService.getById(id);
        if (stock == null) {
            response.sendError(404, "No se a encontrado el stock");
            return;
        }

        request.setAttribute("method", "POST");
        request.setAttribute("action", "");
        request.setAttribute("stock", stock);
        request.setAttribute("articulos", this.articuloService.getAll());

        request.getRequestDispatcher("/views/stock/addedit.jsp").forward(request, response);
    }

    public void getDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var stock = this.stockService.getById(id);
        if (stock == null) {
            response.sendError(404, "No se a encontrado el stock");
            return;
        }

        request.setAttribute("stock", stock);
        request.getRequestDispatcher("/views/stock/delete.jsp").forward(request, response);
    }

    
    public void postDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        boolean result = this.stockService.delete(id);
        if (result) {
            this.showMessage(request, response, "Stock", "Se a eliminado el stock correctamente", "stock");
        } else {
            this.showMessage(request, response, "Ah ocurrido un problema", "Ah habido un problema al eliminar el stock", "stock");
        }
    }
    
    @Override
    void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StockModel> all = this.stockService.getAll();
        //all.sort((a,b)->a.getNombre().compareTo(b.getNombre()));
        request.setAttribute("stockList", all);
        request.getRequestDispatcher("/views/stock/index.jsp").forward(request, response);
    }

    public void postEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean result = this.stockService.update(new StockModel(
                UUID.fromString(request.getParameter("id")),
                this.articuloService.getById(request.getParameter("articulo")),
                Integer.parseInt(request.getParameter("cantidad"))
        ));

        if (result) {
            this.showMessage(request, response, "Stock", "Se a modificado el stock correctamente", "stock");
        } else {
            this.showMessage(request, response, "Ah ocurrido un problema", "Ah habido un problema al modificar el stock", "stock");
        }
    }
    
    public void getCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("stock", new StockModel());
        request.setAttribute("articulos", this.articuloService.getAll());
        request.setAttribute("method", "POST");
        request.setAttribute("action", "");

        request.getRequestDispatcher("/views/stock/addedit.jsp").forward(request, response);
    }

    public void postCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticuloModel byId = this.articuloService.getById(request.getParameter("articulo"));
        boolean any = this.stockService.getAll().stream()
                .anyMatch(x->x.getArticulo().getID().equals(byId.getID()));
        if(any){
            this.showMessage(request, response, "Stock", "Ya existe un articulo registrado", "stock");
            return;
        }
        
        boolean result = this.stockService.insert(new StockModel(
                UUID.fromString(request.getParameter("id")),
                byId,
                Integer.parseInt(request.getParameter("cantidad"))
        ));

        if (result) {
            this.showMessage(request, response, "Stock", "Se a cargado el stock correctamente", "stock");
        } else {
            this.showMessage(request, response, "Ah ocurrido un problema", "Ah habido un problema al crear el stock", "stock");
        }
    }
}
