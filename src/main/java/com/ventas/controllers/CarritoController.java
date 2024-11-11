package com.ventas.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ventas.app.App;
import com.ventas.data.LoginDTO;
import com.ventas.models.ArticuloModel;
import com.ventas.models.CarritoModel;
import com.ventas.services.ArticuloService;
import com.ventas.services.CarritoService;
import com.ventas.services.UsuarioService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Servlet implementation class CarritoController
 */
@WebServlet("/carrito")
public class CarritoController extends BaseController {
	private static final long serialVersionUID = 1L;
	private CarritoService carritoService;
        private UsuarioService usuarioService;
        private ArticuloService articuloService;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarritoController() {
        super();
        this.carritoService = App.getInstance()
                                .getService(CarritoService.class);
        this.usuarioService = App.getInstance()
                                .getService(UsuarioService.class);
        this.articuloService = App.getInstance()
                                .getService(ArticuloService.class);
    }


    @Override
    void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var loginDTO = (LoginDTO) request.getSession().getAttribute("login");
        
        double sum = loginDTO.getCarrito().stream()
                        .mapToDouble(x->x.getCantidad()*x.getArticulo().getPrecio())
                        .sum();
        
        request.setAttribute("carritos", loginDTO.getCarrito());
        request.setAttribute("total", sum);
        request.getRequestDispatcher("/views/carrito/index.jsp").forward(request, response);
    }
        
    public void getShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<CarritoModel> carrito = this.getCarrito(request);

        if (carrito.isEmpty()) {
            response.sendError(404, "No se a encontrado el articulo del carrito");
            return;
        }
        
        request.setAttribute("carrito", carrito.get());
        request.getRequestDispatcher("/views/carrito/show.jsp").forward(request, response);
    }
    
    public void getEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<CarritoModel> carrito = this.getCarrito(request);

        if (carrito.isEmpty()) {
            response.sendError(404, "No se a encontrado el articulo del carrito");
            return;
        }

        request.setAttribute("method", "POST");
        request.setAttribute("action", "");
        request.setAttribute("carrito", carrito.get());
        request.setAttribute("usuarios", this.usuarioService.getAll());
        request.setAttribute("articulos", this.articuloService.getAll());

        request.getRequestDispatcher("/views/carrito/addedit.jsp").forward(request, response);
    }
    
    public void postEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<CarritoModel> carrito = this.getCarrito(request);

        if (carrito.isEmpty()) {
            response.sendError(404, "No se a encontrado el articulo del carrito");
            return;
        }
        
        ArticuloModel articulo = this.articuloService.getById(request.getParameter("articulo"));
        
        CarritoModel cm = carrito.get();
        cm.setArticulo(articulo);
        cm.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        this.showMessage(request, response, "Carrito", "Se a cambiado el articulo correctamente", "carrito");
    }
    
    public void getDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<CarritoModel> carrito = this.getCarrito(request);

        if (carrito.isEmpty()) {
            response.sendError(404, "No se a encontrado el articulo del carrito");
            return;
        }

        request.setAttribute("carrito", carrito.get());
        request.getRequestDispatcher("/views/carrito/delete.jsp").forward(request, response);
    }
    
    public void postDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<CarritoModel> carrito = this.getCarrito(request);

        if (carrito.isEmpty()) {
            response.sendError(404, "No se a encontrado el articulo del carrito");
            return;
        }
        
        var loginDTO = (LoginDTO) request.getSession().getAttribute("login");
        loginDTO.getCarrito().remove(carrito.get());

        this.showMessage(request, response, "Carrito", "Se a eliminado el articulo correctamente", "carrito");
       
    }
    
    
    //Obtiene un carrito desde un id pasado por parametro
    private Optional<CarritoModel> getCarrito(HttpServletRequest request){
        String id = request.getParameter("id");

        if (id == null) return Optional.empty();

        var loginDTO = (LoginDTO) request.getSession().getAttribute("login");
        
        
        return loginDTO
                .getCarrito()
                .stream()
                .filter(x->x.getID().toString().equals(id))
                .findFirst();
    }
}
