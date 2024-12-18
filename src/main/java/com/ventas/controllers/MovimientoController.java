package com.ventas.controllers;

import com.ventas.app.App;
import com.ventas.data.SecureOptional;
import com.ventas.data.SessionDecorator;
import com.ventas.models.MovimientoModel;
import com.ventas.models.UsuarioModel;
import com.ventas.services.MovimientoService;
import com.ventas.services.UsuarioService;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/saldo")
public class MovimientoController extends BaseController {

    private static final long serialVersionUID = 1L;
    private final MovimientoService movimientoService;
    private final UsuarioService usuarioService;

    public MovimientoController() {
        this.movimientoService = App.getInstance().getService(MovimientoService.class);
        this.usuarioService = App.getInstance().getService(UsuarioService.class);
    }

    @Override
    public void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        UsuarioModel usuario = sessionDecorator.getUsuario();

        List<MovimientoModel> movimientos = this.movimientoService.getMovimientos(usuario);

        LocalDateTime fecha_from = SecureOptional
                .ofNullable(request.getParameter("fecha_from"))
                .secureMap(x -> LocalDate.parse(x, DateTimeFormatter.ISO_DATE).atStartOfDay())
                .orElse(LocalDateTime.now().minusDays(30));

        LocalDateTime fecha_to = SecureOptional
                .ofNullable(request.getParameter("fecha_to"))
                .secureMap(x -> LocalDate.parse(x, DateTimeFormatter.ISO_DATE).atStartOfDay())
                .orElse(LocalDateTime.now().plusDays(1));

        movimientos.removeIf(x -> {
            LocalDateTime fecha = x.getFecha();

            return !((fecha.isAfter(fecha_from) || fecha.isEqual(fecha_from))
                    && (fecha.isBefore(fecha_to) || fecha.isEqual(fecha_to)));
        });

        request.setAttribute("fecha_from", fecha_from.format(DateTimeFormatter.ISO_DATE));
        request.setAttribute("fecha_to", fecha_to.format(DateTimeFormatter.ISO_DATE));

        request.setAttribute("movimientos", movimientos);
        request.setAttribute("saldo", this.movimientoService.getSaldo(usuario));

        request.getRequestDispatcher("/views/movimiento/index.jsp").forward(request, response);
    }

    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MovimientoModel> movimientos = this.movimientoService.getAll();

        LocalDateTime fecha_from = SecureOptional
                .ofNullable(request.getParameter("fecha_from"))
                .secureMap(x -> LocalDate.parse(x, DateTimeFormatter.ISO_DATE).atStartOfDay())
                .orElse(LocalDateTime.now().minusDays(30));

        LocalDateTime fecha_to = SecureOptional
                .ofNullable(request.getParameter("fecha_to"))
                .secureMap(x -> LocalDate.parse(x, DateTimeFormatter.ISO_DATE).atStartOfDay())
                .orElse(LocalDateTime.now().plusDays(1));

        movimientos.removeIf(x->{
            LocalDateTime fecha = x.getFecha();
        
            return !((fecha.isAfter(fecha_from) || fecha.isEqual(fecha_from)) 
                   &&(fecha.isBefore(fecha_to) || fecha.isEqual(fecha_to)));
        });


        
        request.setAttribute("movimientos", movimientos);
        request.setAttribute("fecha_from", fecha_from.format(DateTimeFormatter.ISO_DATE));
        request.setAttribute("fecha_to", fecha_to.format(DateTimeFormatter.ISO_DATE));

        request.getRequestDispatcher("/views/movimiento/all.jsp").forward(request, response);
    }

    public void getTransferencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        UsuarioModel usuario = sessionDecorator.getUsuario();

        List<UsuarioModel> all = this.usuarioService.getAll();
        all.removeIf(x -> x.equals(usuario));

        request.setAttribute("usuarios", all);

        request.getRequestDispatcher("/views/movimiento/transferencia.jsp").forward(request, response);
    }

    public void postTransferencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        UsuarioModel usuario = sessionDecorator.getUsuario();

        double saldo = this.movimientoService.getSaldo(usuario);

        double monto = Optional
                .ofNullable(request.getParameter("monto"))
                .map(x -> Double.valueOf(x))
                .orElse(0.0);

        UsuarioModel toUsuario = this.usuarioService.getById(request.getParameter("id_usuario"));

        if (monto > saldo) {
            this.showMessage(request, response, "Transferencia", "Usted no posee suficiente saldo para realizar esta transferencia", "saldo?accion=transferencia");
            return;
        }

        if (monto < 0) {
            this.showMessage(request, response, "Hubo un problema", "los montos negativo no es aceptado", "/saldo");
            return;
        }

        if (monto == 0) {
            this.showMessage(request, response, "Hubo un problema", "El monto no puede ser 0", "/saldo");
            return;
        }

        if (toUsuario == null) {
            this.showMessage(request, response, "ERROR: Transferencia", "Datos ingresados incorrectos", "saldo?accion=transferencia");
            return;
        }

        this.movimientoService.insert(new MovimientoModel(monto, LocalDateTime.now(), usuario, toUsuario));

        this.showMessage(request, response, "Transferencia", "Se ah transferido correctamente el dinero", "saldo");

    }

    public void getShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var articulo = this.movimientoService.getById(id);
        if (articulo == null) {
            this.showMessage(request, response, "Hubo un problema", "No se a encontrado el movimiento", "javascript:window.history.back()");
            return;
        }

        request.setAttribute("movimiento", articulo);
        request.getRequestDispatcher("/views/movimiento/show.jsp").forward(request, response);
    }

    public void getIngreso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/movimiento/ingreso.jsp").forward(request, response);
    }

    public void postIngreso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");

        double monto = Optional
                .ofNullable(request.getParameter("monto"))
                .map(x -> Double.parseDouble(x))
                .orElse(0.0);

        if (monto < 0) {
            this.showMessage(request, response, "Hubo un problema", "Los montos negativo no es aceptado", "/saldo");
            return;
        }

        if (monto == 0) {
            this.showMessage(request, response, "Hubo un problema", "El monto no puede ser 0", "/saldo");
            return;
        }

        this.movimientoService.insert(new MovimientoModel(
                monto,
                LocalDateTime.now(),
                null,
                sessionDecorator.getUsuario())
        );

        this.showMessage(request, response, "Ingreso", "Se registro el ingreso de: $" + monto, "saldo");
    }

}
