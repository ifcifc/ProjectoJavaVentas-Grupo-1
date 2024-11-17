package com.ventas.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ventas.app.App;
import com.ventas.data.SessionDecorator;
import com.ventas.models.ArticuloModel;
import com.ventas.models.CarritoModel;
import com.ventas.services.ArticuloService;
import com.ventas.services.CarritoService;
import com.ventas.services.UsuarioService;
import java.util.Optional;

/**
 * Servlet implementation class CarritoController
 */
@WebServlet("/carrito")
public class CarritoController extends BaseController {
	private static final long serialVersionUID = 1L;
        private final UsuarioService usuarioService;
        private final ArticuloService articuloService;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarritoController() {
        super();
        this.usuarioService = App.getInstance()
                                .getService(UsuarioService.class);
        this.articuloService = App.getInstance()
                                .getService(ArticuloService.class);
    }


    @Override
    void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var loginDTO = (SessionDecorator) request.getSession().getAttribute("login");
        
        double sum = loginDTO.getCarrito().getAll().stream()
                        .mapToDouble(x->x.getCantidad()*x.getArticulo().getPrecio())
                        .sum();
        
        request.setAttribute("carritos", loginDTO.getCarrito().getAll());
        request.setAttribute("total", sum);
        
        request.getRequestDispatcher("/views/carrito/index.jsp").forward(request, response);
    }
        
    public void getShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var loginDTO = (SessionDecorator) request.getSession().getAttribute("login");

        request.setAttribute("carrito", loginDTO.getCarrito().getAll());
        request.getRequestDispatcher("/views/carrito/show.jsp").forward(request, response);
    }
    
    public void getEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var loginDTO = (SessionDecorator) request.getSession().getAttribute("login");

        request.setAttribute("method", "POST");
        request.setAttribute("action", "");
        request.setAttribute("carrito", loginDTO.getCarrito().getAll());
        request.setAttribute("usuarios", this.usuarioService.getAll());
        request.setAttribute("articulos", this.articuloService.getAll());

        request.getRequestDispatcher("/views/carrito/addedit.jsp").forward(request, response);
    }
    
    public void postEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var loginDTO = (SessionDecorator) request.getSession().getAttribute("login");

        ArticuloModel articulo = this.articuloService.getById(request.getParameter("articulo"));
        
        CarritoModel cm = loginDTO.getCarrito().getById(request.getParameter("id"));
        cm.setArticulo(articulo);
        cm.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        this.showMessage(request, response, "Carrito", "Se a cambiado el articulo correctamente", "carrito");
    }
    
    public void getDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var loginDTO = (SessionDecorator) request.getSession().getAttribute("login");

        request.setAttribute("carrito", loginDTO.getCarrito().getAll());
        request.getRequestDispatcher("/views/carrito/delete.jsp").forward(request, response);
    }
    
    public void postDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var loginDTO = (SessionDecorator) request.getSession().getAttribute("login");
        loginDTO.getCarrito().delete(request.getParameter("id"));

        this.showMessage(request, response, "Carrito", "Se a eliminado el articulo correctamente", "carrito");
       
    }
    
    
    
}
