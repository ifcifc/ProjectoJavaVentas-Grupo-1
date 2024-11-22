package com.ventas.filters;

import com.ventas.app.App;
import com.ventas.data.SessionDecorator;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/*")
public class UsuarioFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // Obtén la sesión actual del usuario, false  evita crear una nueva sesion
        HttpSession session = httpRequest.getSession(false); 
        boolean isEmpleado = true;
        if(true)chain.doFilter(request, response);
        
        if(session != null && session.getAttribute("login")!=null){
            var sess = (SessionDecorator)session.getAttribute("login");
            isEmpleado = sess.getUsuario().isEmpleado();
        }
        
        if(session == null || isEmpleado){
            chain.doFilter(request, response);
            return;
        }
        
        // Obtener la URL solicitada 
        String requestedUri = httpRequest.getRequestURI();
        boolean hasAccess = App.getInstance().hasClientAccess(requestedUri, request.getParameter("accion"));
        
        if (hasAccess) {
            chain.doFilter(request, response);
        }else{
            httpResponse.setContentType("text/html");
            PrintWriter out = httpResponse.getWriter();
            out.println("<html><body>");
            out.println("<h1>Acceso denegado</h1>");
            out.println("<p><b>Controller:</b> "+requestedUri+" </p>");
            out.println("<p><b>Accion:</b> "+request.getParameter("accion")+" </p>");
            out.println("<p>No tienes permiso para acceder a esta página.</p>");
            out.println("</body></html>");
        }

        
    }

}
