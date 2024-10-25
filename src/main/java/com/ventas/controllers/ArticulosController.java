package com.ventas.controllers;

import java.io.IOException;
import java.util.ArrayList;

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
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticulosController() {
        super();
        this.articuloService = App.getInstance()
    							  .getService(ArticuloService.class);
        
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("articulos", this.articuloService.getAll());
		
		request.setAttribute("mensaje","Hola");

		request.getRequestDispatcher("/views/articulo/index.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
