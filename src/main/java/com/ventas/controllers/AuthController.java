/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.controllers;

import com.ventas.app.App;
import com.ventas.data.SessionDecorator;
import com.ventas.models.UsuarioModel;
import com.ventas.services.UsuarioService;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author igna
 */
@WebServlet("/auth")
public class AuthController extends BaseController {

    private static final long serialVersionUID = 1L;
    private final UsuarioService usuarioService;

    public AuthController() {
        this.usuarioService = App.getInstance().getService(UsuarioService.class);

    }

    @Override
    public void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/auth/index.jsp").forward(request, response);
    }

    public void getLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
    }

    public void getLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        this.getIndex(request, response);
    }

    public void getRegistro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/auth/registro.jsp").forward(request, response);
    }

    public void postLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Optional<UsuarioModel> user = this.usuarioService.getAll().stream()
                .filter(x -> x.getEmail().endsWith(email) && x.getPassword().equals(password))
                .findFirst();

        if (user.isPresent()) {

            HttpSession session = request.getSession();
            session.setAttribute("login", new SessionDecorator(user.get()));
            session.setMaxInactiveInterval(60*30);//30 minutos antes de que expire la sesion
            
            this.showMessage(request, response, "Inicio de sesion", "Bienvenido de nuevo " + user.get().getNombre(), ".");
        } else {
            this.showMessage(request, response, "Inicio de sesion", "Usuario Desconocido", "auth?accion=login");
        }

    }

    public void postRegistro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");
        
        boolean anyMatch = this.usuarioService.getAll().stream().anyMatch(x->x.getEmail().equals(email));

        if(anyMatch){
            this.showMessage(request, response, "Registro de usuario", "El email ya esta siendo usado", "auth?accion=registro");
            return;
        }
        
        UsuarioModel user = new UsuarioModel(nombre, email, password, false);
        
        this.usuarioService.insert(user);
        
        this.showMessage(request, response, "Registro de usuario", "Bienvenido " + user.getNombre() + " Inicie sesion para continuar", "auth?accion=login");
    }

}
