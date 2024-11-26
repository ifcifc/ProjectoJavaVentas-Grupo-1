
package com.ventas.controllers;

import com.ventas.app.App;
import com.ventas.data.StockData;
import com.ventas.models.HistoryModel;
import com.ventas.services.ArticuloService;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/stock")
public class StockController  extends BaseController{
    private static final long serialVersionUID = 1L;
    private final ArticuloService articuloService;

    public StockController() {
        super();
        this.articuloService = App.getInstance()
                .getService(ArticuloService.class);
    }

    @Override
    void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StockData> all = new ArrayList(this.articuloService.getAll().stream()
                .flatMap(x->x.getStockHistory().stream().map(y->new StockData(y, x)))
                .toList());
        
        LocalDateTime fecha_from = Optional
                .ofNullable(request.getParameter("fecha_from"))
                .map(x-> LocalDate.parse(x, DateTimeFormatter.ISO_DATE).atStartOfDay())
                .orElse(LocalDateTime.now().minusDays(30));

        LocalDateTime fecha_to = Optional
                .ofNullable(request.getParameter("fecha_to"))
                .map(x-> LocalDate.parse(x, DateTimeFormatter.ISO_DATE).atStartOfDay())
                .orElse(LocalDateTime.now().plusDays(1));
        
        all.removeIf(x->{
            LocalDateTime fecha = x.getHistory().getFecha();
        
            return !((fecha.isAfter(fecha_from) || fecha.isEqual(fecha_from)) 
                   &&(fecha.isBefore(fecha_to) || fecha.isEqual(fecha_to)));
        });

        all.sort((o1, o2)->o2.getHistory().getFecha().compareTo(o1.getHistory().getFecha()));
        
        request.setAttribute("fecha_from", fecha_from.format(DateTimeFormatter.ISO_DATE));
        request.setAttribute("fecha_to", fecha_to.format(DateTimeFormatter.ISO_DATE));
        
        request.setAttribute("history_stock", all);
        request.getRequestDispatcher("/views/stock/index.jsp").forward(request, response);
    }
    

    public void getShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var articulo = this.articuloService.getById(id);
        if (articulo == null) {
            response.sendError(404, "No se a encontrado el articulo");
            return;
        }

        request.setAttribute("articulo", articulo);
        request.getRequestDispatcher("/views/stock/show.jsp").forward(request, response);
    } 

}
