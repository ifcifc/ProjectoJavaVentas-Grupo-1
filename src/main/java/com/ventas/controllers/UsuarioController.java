package com.ventas.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ventas.app.App;
import com.ventas.models.UsuarioModel;
import com.ventas.models.UsuarioModel;
import com.ventas.services.UsuarioService;
import com.ventas.services.UsuarioService;
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
            response.sendError(404, "No se a encontrado el usuario");
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
            response.sendError(404, "No se a encontrado el usuario");
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
            response.sendError(404, "No se a encontrado el usuario");
            return;
        }

        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("/views/usuario/delete.jsp").forward(request, response);
    }

    public void getCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("usuario", new UsuarioModel());
        request.setAttribute("method", "POST");
        request.setAttribute("action", "");

        request.getRequestDispatcher("/views/usuario/addedit.jsp").forward(request, response);
    }

    public void postCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Por que no solo toma el ultimo valor dado?, es un misterio.
        String[] parameterValues = request.getParameterValues("empleado");
        
        boolean result = this.usuarioService.insert(new UsuarioModel(
                request.getParameter("nombre"),
                request.getParameter("email"),
                request.getParameter("password"),
                parameterValues[parameterValues.length-1].equals("1")
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
        
        boolean result = this.usuarioService.update(new UsuarioModel(
                UUID.fromString(request.getParameter("id")),
                request.getParameter("nombre"),
                request.getParameter("email"),
                request.getParameter("password"),
                parameterValues[parameterValues.length-1].equals("1")
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

        boolean result = this.usuarioService.delete(id);
        if (result) {
            this.showMessage(request, response, "Usuario", "Se a eliminado el usuario correctamente", "usuarios");
        } else {
            this.showMessage(request, response, "Ah ocurrido un problema", "Ah habido un problema al eliminar el usuario", "usuarios");
        }
    }
}
