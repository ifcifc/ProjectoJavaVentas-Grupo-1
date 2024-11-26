/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.data;

import java.util.Optional;
import java.util.function.Function;

/**
 *
 * @author igna
 * @param <T>
 */
public class SecureOptional<T  extends Object>{
    private final Optional<T> optional;

    public SecureOptional(T value){
        this(value, true);
    }
    
    public SecureOptional(T value, boolean isNullable) {
        if(isNullable){
            this.optional = Optional.ofNullable(value);
        }else{
            this.optional = Optional.of(value);
        }
    }
    
    public static <T extends Object> SecureOptional<T> of(T value){
        return new SecureOptional<>(value, false);
    }
    public static <T extends Object> SecureOptional<T> ofNullable(T value){
        return new SecureOptional<>(value);
    }

    public Optional<T> getOptional() {
        return optional;
    }
    
    public <U extends Object> Optional<U> secureMap(Function<? super T, ? extends U> mapper){
        return this.optional.map(x->{
            try{
                return mapper.apply(x);
            }catch(Exception e){
                return null;
            }
        });
    }
    
}
