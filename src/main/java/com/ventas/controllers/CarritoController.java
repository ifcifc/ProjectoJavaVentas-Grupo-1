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

/**
 * Servlet implementation class CarritoController
 */
@WebServlet("/CarritoController")
public class CarritoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CarritoService carritoService;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarritoController() {
        super();
        this.carritoService = App.getInstance()
				  .getService(CarritoService.class);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("carritos", this.carritoService.getAll());
		request.getRequestDispatcher("/views/carrito/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
