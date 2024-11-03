package com.ventas.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ventas.app.App;
import com.ventas.models.ArticuloModel;
import com.ventas.services.ArticuloService;

/**
 * Servlet implementation class ArticulosController
 */
@WebServlet("/ArticulosController")
public class ArticulosController extends BaseController {
    private static final long serialVersionUID = 1L;
    private final ArticuloService articuloService;

    public ArticulosController() {
        super();
        this.articuloService = App.getInstance()
    							  .getService(ArticuloService.class);
        
    }

	@Override
	public void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("articulos", this.articuloService.getAll());
		request.getRequestDispatcher("/views/articulo/index.jsp").forward(request, response);
	}	
	
	public void getShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id = request.getParameter("id");        
        var articulo = this.articuloService.getById(id);
        if(articulo==null) {
        	response.sendError(404, "No se a encontrado el articulo");
        	return;
        }
        
        request.setAttribute("articulo", articulo);
		request.getRequestDispatcher("/views/articulo/show.jsp").forward(request, response);
	}	
	
	public void getEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}	
	
	public void getDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
	
	public void getCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}

}
