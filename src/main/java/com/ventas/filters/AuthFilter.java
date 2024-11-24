package com.ventas.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Este filtro se encarga de comprobar de si se inicio sesion,
 * En caso contrario redirigira la pagina al login
 */
@WebFilter(urlPatterns = "/*")
public class AuthFilter  implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // Obtén la sesión actual del usuario, false  evita crear una nueva sesion
        HttpSession session = httpRequest.getSession(false); 
                
        //Para verificar que este bien formada la url
        long count = httpRequest.getRequestURI()
        		.replace(httpRequest.getContextPath(), "")
                .chars()
                .filter(x->x=='/')
                .count();
            
            
        String loginURI = httpRequest.getContextPath() + "/auth";
        
        //Comprueba si se intenta acceder al controlador auth
        boolean isAuth = httpRequest.getRequestURI().equals(loginURI);
        
        //Comprueba si se intenta acceder a un recurso css
        boolean isCss = httpRequest.getRequestURI().contains("/css/");
        
        //Si se dirige al controlador AuthController o carga un recurso css no comprueba nada mas
        if(isAuth || isCss){
            chain.doFilter(request, response);
            return;
        }
        
        //Compruebo que este bien formada la url
        if(count>1){
            httpResponse.sendRedirect(httpRequest.getContextPath());
            return;
        }
        
        //Si no existe una sesion redirige la pagina a auth
        if(session==null){
            httpResponse.sendRedirect("auth");
            return;
        }
        
        Object login = session.getAttribute("login");
        //Si el usuario esta logueado no lo detiene
        if(login!=null){
            chain.doFilter(request, response);
        }else{//Si no lo redirige a auth
            httpResponse.sendRedirect("auth");
        }
        
    }
    
}
