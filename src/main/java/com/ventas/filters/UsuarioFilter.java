package com.ventas.filters;

import com.ventas.app.App;
import com.ventas.data.SessionDecorator;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
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

        // Obtener la URL solicitada 
        String requestedUri = httpRequest.getRequestURI()
        		.replace(httpRequest.getContextPath(), "");
        // Obtén la sesión actual del usuario, false  evita crear una nueva sesion
        HttpSession session = httpRequest.getSession(false); 
        boolean isEmpleado = true;
        
        //Si existe una sesion comprueba si es o no un empleado
        if(session != null && session.getAttribute("login")!=null){
            var sess = (SessionDecorator)session.getAttribute("login");
            isEmpleado = sess.getUsuario().isEmpleado();
        }
        
      //Comprueba si se intenta acceder a un recurso css
        boolean isCss = httpRequest.getRequestURI().contains("/css/");
        
        //Si no existe una sesion o es empleado permite seguir sin ninguna otra comprobacion
        if(session == null || isEmpleado || isCss){
            chain.doFilter(request, response);
            return;
        }
        
        //Comprueba si la url a la que esta intentando acceder el cliente es una en la que tenga acceso
        boolean hasAccess = App.getInstance().hasClientAccess(requestedUri, request.getParameter("accion"));
        
        //En caso de que tenga acceso permite seguir con normalidad, de otra forma mostrara un mensaje de error
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
