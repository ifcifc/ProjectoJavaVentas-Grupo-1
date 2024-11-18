
package com.ventas.controllers;

import com.ventas.app.App;
import com.ventas.data.SessionDecorator;
import com.ventas.models.MovimientoModel;
import com.ventas.models.UsuarioModel;
import com.ventas.services.MovimientoService;
import com.ventas.services.UsuarioService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/saldo")
public class MovimientoController extends BaseController {

    private static final long serialVersionUID = 1L;
    private final MovimientoService movimientoService;
    
    public MovimientoController() {
         this.movimientoService = App.getInstance().getService(MovimientoService.class);
    }

    @Override
    public void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        UsuarioModel usuario = sessionDecorator.getUsuario();
        
        List<MovimientoModel> movimientos = this.movimientoService.getAll().stream().filter(x->x.getTo().equals(usuario)).toList();
        
        request.setAttribute("movimientos", movimientos);
        request.setAttribute("saldo", this.movimientoService.getSaldo(usuario));

        request.getRequestDispatcher("/views/movimiento/index.jsp").forward(request, response);
    }
    
    
    public void getIngreso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/movimiento/ingreso.jsp").forward(request, response);
    }

    public void postIngreso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sessionDecorator = (SessionDecorator) request.getSession().getAttribute("login");
        
        double monto = Optional
                .ofNullable(request.getParameter("monto"))
                .map(x -> Double.valueOf(x))
                .orElse(0.0);
        
        
        this.movimientoService.insert(new MovimientoModel(
                monto, 
                LocalDateTime.now(), 
                null, 
                sessionDecorator.getUsuario())
        );
        
        
        this.showMessage(request, response, "Ingreso", "Se registro el ingreso de: $" + monto, "saldo");
    }

}
