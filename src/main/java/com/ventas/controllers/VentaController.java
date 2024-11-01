package com.ventas.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ventas.app.App;
import com.ventas.services.CarritoService;
import com.ventas.services.UsuarioService;
import com.ventas.services.VentaService;

/**
 * Servlet implementation class CarritoController
 */
@WebServlet("/VentaController")
public class VentaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VentaService ventaService;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VentaController() {
        super();
        this.ventaService = App.getInstance()
				  .getService(VentaService.class);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("ventas", this.ventaService.getAll());
		request.getRequestDispatcher("/views/venta/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
