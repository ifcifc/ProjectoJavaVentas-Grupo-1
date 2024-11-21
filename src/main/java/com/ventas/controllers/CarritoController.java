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
import com.ventas.services.MovimientoService;
import com.ventas.services.UsuarioService;
import com.ventas.utils.UUIDUtils;
import java.util.Optional;
import java.util.UUID;

/**
 * Servlet implementation class CarritoController
 */
@WebServlet("/carrito")
public class CarritoController extends BaseController {

    private static final long serialVersionUID = 1L;
    private final UsuarioService usuarioService;
    private final ArticuloService articuloService;
    private final MovimientoService movimientoService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarritoController() {
        super();
        this.usuarioService = App.getInstance()
                .getService(UsuarioService.class);
        this.articuloService = App.getInstance()
                .getService(ArticuloService.class);
        this.movimientoService = App.getInstance()
                .getService(MovimientoService.class);
    }

    @Override
    void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");

        double sum = sessionDecorator.getCarrito().getAll().stream()
                .mapToDouble(x -> x.getCantidad() * x.getArticulo().getPrecio())
                .sum();

        request.setAttribute("carritos", sessionDecorator.getCarrito().getAll());
        request.setAttribute("total", sum);

        request.getRequestDispatcher("/views/carrito/index.jsp").forward(request, response);
    }

    public void getShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        UUID id_articulo = Optional
                .ofNullable(UUIDUtils.fromString(request.getParameter("id")))
                .orElse(UUID.randomUUID());

        request.setAttribute("carrito", sessionDecorator.getCarrito().getById(id_articulo));
        request.getRequestDispatcher("/views/carrito/show.jsp").forward(request, response);
    }

    public void getEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        UUID id_articulo = Optional
                .ofNullable(UUIDUtils.fromString(request.getParameter("id")))
                .orElse(UUID.randomUUID());

        request.setAttribute("carrito", sessionDecorator.getCarrito().getById(id_articulo));
        request.setAttribute("method", "POST");
        request.setAttribute("action", "");
        request.setAttribute("articulos", this.articuloService.getAll());

        request.getRequestDispatcher("/views/carrito/addedit.jsp").forward(request, response);
    }

    public void postEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");

        ArticuloModel articulo = this.articuloService.getById(request.getParameter("articulo"));

        CarritoModel cm = sessionDecorator.getCarrito().getById(request.getParameter("id"));
        cm.setArticulo(articulo);
        cm.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        this.showMessage(request, response, "Carrito", "Se a cambiado el articulo correctamente", "carrito");
    }

    public void getDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");

        UUID id_articulo = Optional
                .ofNullable(UUIDUtils.fromString(request.getParameter("id")))
                .orElse(UUID.randomUUID());

        request.setAttribute("carrito", sessionDecorator.getCarrito().getById(id_articulo));
        request.getRequestDispatcher("/views/carrito/delete.jsp").forward(request, response);
    }

    public void postDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        sessionDecorator.getCarrito().delete(request.getParameter("id"));

        this.showMessage(request, response, "Carrito", "Se a eliminado el articulo correctamente", "carrito");

    }

    public void getCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        double saldo = this.movimientoService.getSaldo(sessionDecorator.getUsuario());
        request.setAttribute("saldo", saldo);
        //Convierto la lista de carrito y stock en un diccionario para simplificar la logica del jsp
        request.setAttribute("carritos", sessionDecorator.getCarrito().getAll());

        request.getRequestDispatcher("/views/carrito/carritoView.jsp").forward(request, response);
    }

    public void postCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        var carrito = sessionDecorator.getCarrito();

        String url = Optional
                .ofNullable(request.getParameter("from"))
                .map(x -> x + "?accion=" + (x.equals("carrito") ? "carrito" : "client"))
                .orElse(".");
        
        
        UUID id_articulo = Optional
                .ofNullable(UUIDUtils.fromString(request.getParameter("id")))
                .orElse(UUID.randomUUID());

        int cantidad = Integer.parseInt(Optional.ofNullable(request.getParameter("cantidad")).orElse("0"));

        CarritoModel carritoModel = carrito.getAll().stream()
                .filter(x -> x.getArticulo().getID().equals(id_articulo))
                .findFirst()
                .orElse(new CarritoModel(this.articuloService.getById(id_articulo)));


        cantidad = Math.clamp(cantidad, 0, carritoModel.getArticulo().getStock());
        
        if (cantidad > 0) {
            
            double saldo = this.movimientoService.getSaldo(sessionDecorator.getUsuario());
            
            double precio = carritoModel.getArticulo().getPrecio();
            
            double total = carrito.getTotal() + precio*cantidad - precio*carritoModel.getCantidad() ;
            
            if(total>saldo){
                this.showMessage(request, response, "Hubo un problema", "No posee suficiente saldo para añadir el articulo a su carrito", url);
                return;
            }
            
            carritoModel.setCantidad(cantidad);

            if (!carrito.any(carritoModel.getID())) {
                carrito.insert(carritoModel);
            }
        } else if (cantidad == 0) {
            carrito.delete(carritoModel.getID());
        }

        

        response.sendRedirect(url);
    }

}
