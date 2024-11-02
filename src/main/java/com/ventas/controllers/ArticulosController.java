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
public class ArticulosController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ArticuloService articuloService;

    public ArticulosController() {
        super();
        this.articuloService = App.getInstance()
    							  .getService(ArticuloService.class);
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getParameter("id") == null) this.getIndex(request, response);
			
			String accion = Optional
						.ofNullable(request.getParameter("accion"))
						.orElse("index");
			
	        switch (accion) {
				case "show" -> getShow(request, response);
				case "edit" -> getEdit(request, response);
				case "create" -> getCreate(request, response);
				case "delete" -> getDelete(request, response);
				default -> getIndex(request, response);
			}
		}catch(Exception e) {
			response.sendError(500, e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("articulos", this.articuloService.getAll());
		request.getRequestDispatcher("/views/articulo/index.jsp").forward(request, response);
	}	
	
	private void getShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id = request.getParameter("id");        
        var articulo = this.articuloService.getById(id);
        if(articulo==null) {
        	response.sendError(404, "No se a encontrado el articulo");
        	return;
        }
        
        request.setAttribute("articulos", articulo);
		request.getRequestDispatcher("/views/articulo/show.jsp").forward(request, response);
	}	
	
	private void getEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}	
	
	private void getDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
	
	private void getCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}

}
