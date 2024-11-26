package com.ventas.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ventas.app.App;
import com.ventas.models.UsuarioModel;
import com.ventas.services.UsuarioService;
import com.ventas.utils.UUIDUtils;
import java.util.Optional;
import java.util.UUID;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/usuarios")
public class UsuarioController extends BaseController {
	private static final long serialVersionUID = 1L;
	private UsuarioService usuarioService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        this.usuarioService = App.getInstance()
				  .getService(UsuarioService.class);
    }


    @Override
    void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("usuarios", this.usuarioService.getAll());
        request.getRequestDispatcher("/views/usuario/index.jsp").forward(request, response);    
    }

    
    public void getShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var usuario = this.usuarioService.getById(id);
        if (usuario == null) {
            this.showMessage(request, response, "Hubo un problema", "No se a encontrado el usuario", "javascript:window.history.back()");


            return;
        }

        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("/views/usuario/show.jsp").forward(request, response);
    }

    public void getEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var usuario = this.usuarioService.getById(id);
        if (usuario == null) {
            this.showMessage(request, response, "Hubo un problema", "No se a encontrado el usuario", "javascript:window.history.back()");

            return;
        }

        request.setAttribute("method", "POST");
        request.setAttribute("action", "");
        request.setAttribute("usuario", usuario);

        request.getRequestDispatcher("/views/usuario/addedit.jsp").forward(request, response);
    }

    public void getDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }

        var usuario = this.usuarioService.getById(id);
        if (usuario == null) {
            this.showMessage(request, response, "Hubo un problema", "No se a encontrado el usuario", "javascript:window.history.back()");

            return;
        }

        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("/views/usuario/delete.jsp").forward(request, response);
    }

    public void getCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("usuario", new UsuarioModel());


        request.getRequestDispatcher("/views/usuario/addedit.jsp").forward(request, response);
    }

    public void postCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Por que no solo toma el ultimo valor dado?, es un misterio.
        String[] parameterValues = request.getParameterValues("empleado");
        
        Optional<String> email = Optional.ofNullable(request.getParameter("email"));
        Optional<String> nombre = Optional.ofNullable(request.getParameter("nombre"));
        Optional<String> password = Optional.ofNullable(request.getParameter("password"));
        
        if(email.isEmpty() || nombre.isEmpty() || password.isEmpty()){
            this.showMessage(request, response, "Hubo un problema", "Faltan datos para crear el usuario", "usuarios");
            return;
        }
        
        boolean isEmpleado = Optional.ofNullable(parameterValues)
            .map(x-> x[x.length-1].equals("1"))
            .orElse(false);
        
        boolean anyMatch = this.usuarioService.getAll().stream().anyMatch(x->x.getEmail().equals(email.get()));
        
        if(anyMatch){
            this.showMessage(request, response, "Hubo un problema", "Ya existe un usuario con este email", "usuarios");
            return;
        }
        
        boolean result = this.usuarioService.insert(new UsuarioModel(
                nombre.get(),
                email.get(),
                password.get(),
                isEmpleado
        ));

        if (result) {
            this.showMessage(request, response, "Usuario", "Se a cargado el usuario correctamente", "usuarios");
        } else {
            this.showMessage(request, response, "Ah ocurrido un problema", "Ah habido un problema al crear el usuario", "usuarios");
        }
    }

    public void postEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Por que no solo toma el ultimo valor dado?, es un misterio.
        String[] parameterValues = request.getParameterValues("empleado");
        
        Optional<String> email = Optional.ofNullable(request.getParameter("email"));
        Optional<String> nombre = Optional.ofNullable(request.getParameter("nombre"));
        Optional<String> password = Optional.ofNullable(request.getParameter("password"));
        
        if(email.isEmpty() || nombre.isEmpty() || password.isEmpty()){
            this.showMessage(request, response, "Hubo un problema", "Faltan datos para crear el usuario", "usuarios");
            return;
        }
        
        boolean isEmpleado = Optional.ofNullable(parameterValues)
            .map(x-> x[x.length-1].equals("1"))
            .orElse(false);
        
        UUID uid = UUIDUtils.fromString(request.getParameter("id"));
        
        UsuarioModel byId = this.usuarioService.getById(uid);
        
        if(byId.isEmpleado() && !isEmpleado){
            boolean anyEmpleado = this.usuarioService.getAll().stream().anyMatch(x->!x.equals(byId) && x.isEmpleado());
            if(!anyEmpleado){
                this.showMessage(request, response, "Ah ocurrido un problema", "No hay empleados aparte de este usuario, no puede dejar de ser empleado.", "usuarios");
                return;
            }
        }
        
        boolean anyMatch = this.usuarioService.getAll().stream().anyMatch(x->
                !x.equals(byId) &&
                x.getEmail().equals(email.get()));
        
        if(anyMatch){
            this.showMessage(request, response, "Hubo un problema", "Ya existe un usuario con este email", "usuarios");
            return;
        }
        
        boolean result = this.usuarioService.update(new UsuarioModel(
                uid,
                nombre.get(),
                email.get(),
                password.get(),
                isEmpleado
        ));
        
        if (result) {
            this.showMessage(request, response, "Usuario", "Se a modificado el usuario correctamente", "usuarios");
        } else {
            this.showMessage(request, response, "Ah ocurrido un problema", "Ah habido un problema al modificar el usuario", "usuarios");
        }
    }
    
    public void postDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            getIndex(request, response);
            return;
        }
        
        UUID uid = UUIDUtils.fromString(id);
        
        UsuarioModel byId = this.usuarioService.getById(uid);
        
        if(byId.isEmpleado()){
            boolean anyEmpleado = this.usuarioService.getAll().stream().anyMatch(x->!x.equals(byId) && x.isEmpleado());
            if(!anyEmpleado){
                this.showMessage(request, response, "Ah ocurrido un problema", "No hay empleados aparte de este usuario, no puede ser eliminado", "usuarios");
                return;
            }
        }

        boolean result = this.usuarioService.delete(id);
        if (result) {
            this.showMessage(request, response, "Usuario", "Se a eliminado el usuario correctamente", "usuarios");
        } else {
            this.showMessage(request, response, "Ah ocurrido un problema", "Ah habido un problema al eliminar el usuario", "usuarios");
        }
    }
}
