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
import java.util.List;
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
        request.setAttribute("articulos", this.articuloService.getAll());

        request.getRequestDispatcher("/views/carrito/addedit.jsp").forward(request, response);
    }

    public void postEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");

        UUID id_articulo = Optional
                .ofNullable(UUIDUtils.fromString(request.getParameter("id")))
                .orElse(UUID.randomUUID());
        
        Optional<ArticuloModel> articulo = Optional.ofNullable(this.articuloService.getById(id_articulo));

        if(articulo.isEmpty()){
            this.showMessage(request, response, "Hubo un problema", "No se pudo encontrar el articulo", "carrito");
            return;
        }
        
        CarritoModel cm = sessionDecorator.getCarrito().getById(request.getParameter("id"));
        cm.setArticulo(articulo.get());
        cm.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        this.showMessage(request, response, "Carrito", "Se a cambiado el articulo correctamente", "carrito");
    }

    public void getDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");

        UUID id_carrito = Optional
                .ofNullable(UUIDUtils.fromString(request.getParameter("id")))
                .orElse(UUID.randomUUID());

        request.setAttribute("carrito", sessionDecorator.getCarrito().getById(id_carrito));
        request.getRequestDispatcher("/views/carrito/delete.jsp").forward(request, response);
    }

    public void postDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        boolean delete = sessionDecorator.getCarrito().delete(request.getParameter("id"));

        if(delete){
            this.showMessage(request, response, "Carrito", "Se a eliminado el articulo correctamente", "carrito");
        }else{
            this.showMessage(request, response, "Carrito", "Se a eliminado el articulo del carrito", "carrito");
        }
        

    }

    public void getCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        double saldo = this.movimientoService.getSaldo(sessionDecorator.getUsuario());
        
        Optional<String> oContain = Optional
                .ofNullable(request.getParameter("contain"));
        
        List<CarritoModel> all = sessionDecorator.getCarrito().getAll();

        if(oContain.isPresent()){
            String contain = oContain.get().toLowerCase();
            all.removeIf(y->{
                var x = y.getArticulo();
                var c = x.getNombre().toLowerCase().contains(contain) ||
                        x.getDescripcion().toLowerCase().contains(contain) ||
                        String.valueOf(x.getCod()).contains(contain);
                return !c;
            });
            request.setAttribute("contain", contain);
        }
        
        request.setAttribute("saldo", saldo);
        request.setAttribute("carritos", all);
        
        request.getRequestDispatcher("/views/carrito/carritoView.jsp").forward(request, response);
    }

    public void postCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        var carrito = sessionDecorator.getCarrito();

        String url = Optional
                .ofNullable(request.getParameter("from"))
                .map(x -> x + "?accion=" + (x.equals("carrito") ? "carrito" : "client"))
                .orElse("javascript:window.history.back()");
        
        
        UUID id_articulo = Optional
                .ofNullable(UUIDUtils.fromString(request.getParameter("id")))
                .orElse(UUID.randomUUID());

        if(!this.articuloService.any(id_articulo)){
            this.showMessage(request, response, "Hubo un problema", "No existe el articulo", "javascript:window.history.back()");
            return;
        }
        
        
        int cantidad = Integer.parseInt(Optional.ofNullable(request.getParameter("cantidad")).orElse("0"));

        CarritoModel carritoModel = carrito.getAll().stream()
                .filter(x -> x.getArticulo().getID().equals(id_articulo))
                .findFirst()
                .orElse(new CarritoModel(this.articuloService.getById(id_articulo)));


        cantidad = Math.max(0, Math.min(cantidad, carritoModel.getArticulo().getStock()));
        if (cantidad > 0) {
            
            double saldo = this.movimientoService.getSaldo(sessionDecorator.getUsuario());
            
            double precio = carritoModel.getArticulo().getPrecio();
            
            double total = carrito.getTotal() + precio*cantidad - precio*carritoModel.getCantidad();
            
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
