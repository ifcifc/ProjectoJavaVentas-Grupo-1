package com.ventas.controllers;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Controlador base, detecta los metodo que se le pida a travez de un parametro accion
//Creado para probar reflection
public abstract class BaseController  extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//Pone la primera letra en mayuscula
	private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
	
	/**
	 * Este metodo es llamado cuando la consulta se realiza a travez de un get, 
	 * busca un metodo que coincida con el nombre pasado por el accion y llamara al metodo correspondiente
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.parseAccion(request, response, "get");
	}

	/**
	 * Este metodo es llamado cuando la consulta se realiza a travez de un post, 
	 * busca un metodo que coincida con el nombre pasado por el accion y llamara al metodo correspondiente
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.parseAccion(request, response, "post");
	}
	
	/**
	 * Este metodo es llamado cuando la consulta se realiza a travez de un delete, 
	 * busca un metodo que coincida con el nombre pasado por el accion y llamara al metodo correspondiente
	 * */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.parseAccion(request, response, "delete");
	}

	/**
	 * Este metodo es llamado cuando la consulta se realiza a travez de un put, 
	 * busca un metodo que coincida con el nombre pasado por el accion y llamara al metodo correspondiente
	 * */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.parseAccion(request, response, "put");
	}
	

	/**
     * Este método es llamado cuando se recibe una solicitud a través de un método específico.
     * Busca en la clase un método que coincida con el nombre pasado en el parámetro "accion".
     * El metodo al que se llama debe de ser un metodo publico.
     * Si encuentra una coincidencia, invoca el método correspondiente.
     *
     * @param request el objeto HttpServletRequest que contiene la información de la solicitud del cliente
     * @param response el objeto HttpServletResponse para enviar la respuesta al cliente
     * @param method_type un String que representa el tipo de método HTTP (por ejemplo, "GET", "POST", "PUT", "DELETE")
     */
	protected void parseAccion(HttpServletRequest request, HttpServletResponse response, String method_type) throws ServletException, IOException {
        //Obteniene el parametro accion
		String accion = request.getParameter("accion");
        
		//Si no existe el parametro lo redirige a index
        if(accion == null) {
        	this.getIndex(request, response);
        	return;
        }
        
        //Hay que volver a crear una variable por que le da ansiedad a java si la variable no es final
        //le añado el tipo de metodo + la accion con la primera letra en mayuscula
        final String method_accion = method_type + capitalizeFirstLetter(accion);
        
        try {
        	//Defino los parametros que recibe el metodo
            Class<?>[] parameterTypes = { HttpServletRequest.class, HttpServletResponse.class };
        	
            //Busco el metodo en la clase
        	Optional<Method> optionalMethod = Arrays.stream(this.getClass().getMethods())
    	            .filter(x -> x.getName().equals(method_accion))
    	            .findFirst();
        	
        	//Si se encontro el metodo lo llamo pasando los argumentos request, response
        	if(optionalMethod.isPresent()) {
        		optionalMethod
        			.get()
        			.invoke(this, new Object[]{request, response});
        	}else {
        		//Si no encontro el metodo redirije la pagina al index
        		this.getIndex(request, response);
        	}
		} catch (Exception e) {
			//En caso de algun error envia un error 500(Internal Server Error)
			response.sendError(500, e.getMessage());
			e.printStackTrace();
		}
	}
	
	//todos los controladores deben de definir un metodo index
	abstract void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
