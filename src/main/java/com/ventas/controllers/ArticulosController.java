package com.ventas.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ventas.app.App;
import com.ventas.data.SessionDecorator;
import com.ventas.models.ArticuloModel;
import com.ventas.models.HistoryModel;
import com.ventas.models.UsuarioModel;
import com.ventas.services.ArticuloService;
import com.ventas.services.CarritoService;
import com.ventas.services.MovimientoService;
import com.ventas.utils.UUIDUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/articulos")
public class ArticulosController extends BaseController {

    private static final long serialVersionUID = 1L;
    private final ArticuloService articuloService;
    private final CarritoService carritoService;
    private final MovimientoService movimientoService;

    public ArticulosController() {
        super();
        this.articuloService = App.getInstance()
                .getService(ArticuloService.class);
        this.carritoService = App.getInstance()
                .getService(CarritoService.class);
        this.movimientoService = App.getInstance()
                .getService(MovimientoService.class);

    }

    @Override
    public void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ArticuloModel> all = this.articuloService.getAll();

        Optional<String> oContain = Optional
                .ofNullable(request.getParameter("contain"));
        
        if(oContain.isPresent()){
            String contain = oContain.get().toLowerCase();
            all.removeIf(x->{
                var c = x.getNombre().toLowerCase().contains(contain) ||
                        x.getDescripcion().toLowerCase().contains(contain) ||
                        String.valueOf(x.getCod()).contains(contain);
                return !c;
            });
            request.setAttribute("contain", contain);
        }
        
        request.setAttribute("articulos", all);

        request.getRequestDispatcher("/views/articulo/index.jsp").forward(request, response);
    }
    
    

    public void getShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var articulo = this.articuloService.getById(id);
        if (articulo == null) {
            this.showMessage(request, response, "Hubo un problema", "Articulo no encontrado", "javascript:window.history.back()");

            return;
        }

        request.setAttribute("articulo", articulo);
        request.getRequestDispatcher("/views/articulo/show.jsp").forward(request, response);
    } 

    public void getHistorial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var articulo = this.articuloService.getById(id);
        if (articulo == null) {
            this.showMessage(request, response, "Hubo un problema", "Articulo no encontrado", "javascript:window.history.back()");

            return;
        }

        ArrayList<HistoryModel<Integer>> all = articulo.getStockHistory();
        
        Comparator<HistoryModel<Integer>> cmp = (o1, o2)->{
            return o1.getFecha().compareTo(o2.getFecha());
        };
        
        all.sort(cmp.reversed());
        
        request.setAttribute("history_stock", all);
        request.getRequestDispatcher("/views/articulo/historial.jsp").forward(request, response);
    }
    
    public void getHistorialPrecio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var articulo = this.articuloService.getById(id);
        if (articulo == null) {
            this.showMessage(request, response, "Hubo un problema", "Articulo no encontrado", "javascript:window.history.back()");

            return;
        }

        ArrayList<HistoryModel<Double>> all = articulo.getPrecioHistory();
        
        Comparator<HistoryModel<Double>> cmp = (o1, o2)->{
            return o1.getFecha().compareTo(o2.getFecha());
        };
        
        all.sort(cmp.reversed());
        
        request.setAttribute("history_precio", all);
        request.getRequestDispatcher("/views/articulo/historialPrecio.jsp").forward(request, response);
    }
    
       
    public void getHistorialDescripcion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var articulo = this.articuloService.getById(id);
        if (articulo == null) {
            this.showMessage(request, response, "Hubo un problema", "Articulo no encontrado", "javascript:window.history.back()");

            return;
        }

        ArrayList<HistoryModel<String>> all = articulo.getDescripcionHistory();
        
        Comparator<HistoryModel<String>> cmp = (o1, o2)->{
            return o1.getFecha().compareTo(o2.getFecha());
        };
        
        all.sort(cmp.reversed());
        
        request.setAttribute("history_descripcion", all);
        request.getRequestDispatcher("/views/articulo/historialDescripcion.jsp").forward(request, response);
    }
    
    

    public void getEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var articulo = this.articuloService.getById(id);
        if (articulo == null) {
            this.showMessage(request, response, "Hubo un problema", "Articulo no encontrado", "javascript:window.history.back()");

            return;
        }

        request.setAttribute("articulo", articulo);

        request.getRequestDispatcher("/views/articulo/addedit.jsp").forward(request, response);
    }

    public void getDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var articulo = this.articuloService.getById(id);
        if (articulo == null) {
            this.showMessage(request, response, "Hubo un problema", "Articulo no encontrado", "javascript:window.history.back()");

            return;
        }

        request.setAttribute("articulo", articulo);
        request.getRequestDispatcher("/views/articulo/delete.jsp").forward(request, response);
    }

    public void getCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("articulo", new ArticuloModel());

        request.getRequestDispatcher("/views/articulo/addedit.jsp").forward(request, response);
    }

    public void getStock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var articulo = this.articuloService.getById(id);
        if (articulo == null) {
            this.showMessage(request, response, "Hubo un problema", "Articulo no encontrado", "javascript:window.history.back()");

            return;
        }

        request.setAttribute("articulo", articulo);

        request.getRequestDispatcher("/views/articulo/stock.jsp").forward(request, response);
    }

    public void getPrecio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var articulo = this.articuloService.getById(id);
        if (articulo == null) {
            this.showMessage(request, response, "Hubo un problema", "Articulo no encontrado", "javascript:window.history.back()");

            return;
        }

        request.setAttribute("articulo", articulo);

        request.getRequestDispatcher("/views/articulo/precio.jsp").forward(request, response);
    }

    public void postPrecio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var articulo = this.articuloService.getById(id);
        if (articulo == null) {
            this.showMessage(request, response, "Hubo un problema", "Articulo no encontrado", "javascript:window.history.back()");

            return;
        }

        double precio = Optional
                .ofNullable(request.getParameter("precio"))
                .map(x -> Math.max(0, Double.valueOf(x)))
                .orElse(0.0);
        
        if(precio==0){
            this.showMessage(request, response, "Hubo un problema", "El precio no puede ser 0", "articulos");
            return;
        }
        
        
        articulo.setPrecio(precio);
        this.articuloService.update(articulo);

        this.showMessage(request, response, "Articulo", "Se a modificado el stock correctamente", "articulos");
    }

    public void postStock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        UsuarioModel usuario = sessionDecorator.getUsuario();
        
        if (id == null || action==null) {
            getIndex(request, response);
            return;
        }
        
        var articulo = this.articuloService.getById(id);
        if (articulo == null) {
            this.showMessage(request, response, "Hubo un problema", "Articulo no encontrado", "javascript:window.history.back()");

            return;
        }

        int stock = Optional
                .ofNullable(request.getParameter("stock"))
                .map(x -> Math.max(0, Integer.parseInt(x)))
                .orElse(0);

        
        boolean state = false;
        switch(action){
            case "add" -> state = articulo.addStock(stock, usuario.getNombre());
            case "set" -> state = articulo.setStock(stock, usuario.getNombre());
            case "sub" -> state = articulo.addStock(-stock, usuario.getNombre());
        }
        
        if(!state){
            this.showMessage(request, response, "Hubo un problema", "No se pudo cambiar el valor del stock", "articulos");
            return;
        }
        
        this.articuloService.update(articulo);

        this.showMessage(request, response, "Articulo", "Se a modificado el stock correctamente", "articulos");
    }

    public void postCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long code = Optional
                .ofNullable(request.getParameter("cod"))
                .map(x -> Long.valueOf(x))
                .orElse(0L);

        String nombre = Optional
                .ofNullable(request.getParameter("nombre"))
                .orElse("Desconocido");

        String descripcion = Optional
                .ofNullable(request.getParameter("descripcion"))
                .orElse("");

        boolean anyMatch = this.articuloService.getAll()
                .stream()
                .anyMatch(
                        x -> x.getCod() == code
                        || x.getNombre().equals(nombre) && x.getDescripcion().equals(descripcion));

        if (anyMatch) {
            this.showMessage(request, response, "Articulo", "Ya existe este articulo", "articulos");
            return;
        }

        double precio = Optional
                .ofNullable(request.getParameter("precio"))
                .map(x -> Math.max(0, Double.valueOf(x)))
                .orElse(0.0);
        
        if(precio==0){
            this.showMessage(request, response, "Hubo un problema", "El precio no puede ser 0", "articulos");
            return;
        }

        int stock = Optional
                .ofNullable(request.getParameter("stock"))
                .map(x -> Integer.valueOf(x))
                .orElse(0);

        boolean result = this.articuloService.insert(new ArticuloModel(
                code,
                nombre,
                descripcion,
                precio,
                stock
        ));

        if (result) {
            this.showMessage(request, response, "Articulo", "Se a cargado el articulo correctamente", "articulos");
        } else {
            this.showMessage(request, response, "Ah ocurrido un problema", "Ah habido un problema al crear el articulo", "articulos");
        }
    }

    public void postEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long code = Optional
                .ofNullable(request.getParameter("cod"))
                .map(x -> Long.valueOf(x))
                .orElse(0L);

        String nombre = Optional
                .ofNullable(request.getParameter("nombre"))
                .orElse("Desconocido");

        String descripcion = Optional
                .ofNullable(request.getParameter("descripcion"))
                .orElse("");
        
        UUID uid = UUIDUtils.fromString(request.getParameter("id"));

        ArticuloModel byId = this.articuloService.getById(uid);
        
        if(byId==null){
            this.showMessage(request, response, "Articulo", "No se encontro el articulo", "articulos");
            return;
        }
        
        boolean anyMatch = this.articuloService.getAll()
                .stream()
                .anyMatch(x -> !x.equals(byId) &&
                        (x.getCod() == code
                        || x.getNombre().equals(nombre) && x.getDescripcion().equals(descripcion)));

        if (anyMatch) {
            this.showMessage(request, response, "Articulo", "Ya existe este articulo", "articulos");
            return;
        }

        double precio = Optional
                .ofNullable(request.getParameter("precio"))
                .map(x -> Math.max(0, Double.valueOf(x)))
                .orElse(0.0);
        
        if(precio==0){
            this.showMessage(request, response, "Hubo un problema", "El precio no puede ser 0", "articulos");
            return;
        }
        
        byId.setDescripcion(descripcion);
        
        boolean result = this.articuloService.update(new ArticuloModel(
                UUIDUtils.fromString(request.getParameter("id")),
                code,
                nombre,
                descripcion,
                precio,
                0
        ));

        if (result) {
            this.showMessage(request, response, "Articulo", "Se a modificado el articulo correctamente", "articulos");
        } else {
            this.showMessage(request, response, "Ah ocurrido un problema", "Ah habido un problema al modificar el articulo", "articulos");
        }
    }

    public void postDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        boolean result = this.articuloService.delete(id);
        if (result) {
            this.showMessage(request, response, "Articulo", "Se a eliminado el articulo correctamente", "articulos");
        } else {
            this.showMessage(request, response, "Ah ocurrido un problema", "Ah habido un problema al eliminar el articulo", "articulos");
        }
    }

    public void getClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ArticuloModel> all = this.articuloService.getAll();

        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        double saldo = this.movimientoService.getSaldo(sessionDecorator.getUsuario());
        
        Optional<String> oContain = Optional
                .ofNullable(request.getParameter("contain"));
        
        if(oContain.isPresent()){
            String contain = oContain.get().toLowerCase();
            all.removeIf(x->{
                var c = x.getNombre().toLowerCase().contains(contain) ||
                        x.getDescripcion().toLowerCase().contains(contain) ||
                        String.valueOf(x.getCod()).contains(contain);
                return !c;
            });
            request.setAttribute("contain", contain);
        }
        
        
        request.setAttribute("saldo", saldo);
        request.setAttribute("carrito", sessionDecorator.getCarrito().toArticuloMap());
        request.setAttribute("articulos", all);

        request.getRequestDispatcher("/views/articulo/clientView.jsp").forward(request, response);
    }

}
