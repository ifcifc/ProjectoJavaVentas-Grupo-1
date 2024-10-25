/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.app;

/**
 *
 * @author igna
 */
public class App {
    private static AppTest Singleton;

    public static AppTest getInstance() {
        if(Singleton==null){
            Singleton=new AppTest();
            Singleton.run();
        }
        return Singleton;
    }
}
