/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.utils;

import com.ventas.models.BaseModel;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 
 * @author igna
 */
public class ModelUpdate {
    /**
     * Actualiza los valores de una instancia de BaseModel a otra
     * @param from instancia de origen de datos
     * @param to instancia a actualizar
     */
    public static void update(BaseModel from, BaseModel to) throws IllegalArgumentException, IllegalAccessException{
        Class<? extends BaseModel> _class = from.getClass();
        Field[] declaredFields = _class.getDeclaredFields();
        //Recorro los atributos de la clase
        for(Field field : declaredFields){
            //Si el campo es final lo omite
            if(Modifier.isFinal(field.getModifiers()))continue;
            //Permite acceder al campo si es privado
            field.setAccessible(true);
            //Obtengo el valor de la instancia(from)
            Object value = field.get(from);
            //Guardo el valor en la instancia(to)
            field.set(to, value);
        }
   }
}
