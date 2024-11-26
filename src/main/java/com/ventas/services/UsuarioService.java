/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.services;

import java.util.Comparator;
import com.ventas.comparators.ComparatorUsuario;
import java.util.List;

import com.ventas.comparators.ComparatorUsuario;
import com.ventas.models.CarritoModel;
import com.ventas.models.UsuarioModel;


/**
 *
 * @author igna
 */
public class UsuarioService extends BaseService<UsuarioModel>{
	 @Override
	    public List<UsuarioModel> getAll() {
	        Comparator<UsuarioModel> comparator = new ComparatorUsuario()
	        		.thenComparing((UsuarioModel o1, UsuarioModel o2) ->
	        			o1.getEmail().compareTo(o2.getEmail()));
	         
	        List<UsuarioModel> all = super.getAll();
	         
	        all.sort(comparator);
	        
	        return all;
	    }
	    
    
}
