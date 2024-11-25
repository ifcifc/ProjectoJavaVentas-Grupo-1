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
    private static AppBase Singleton;

    public static AppBase getInstance() {
        if(Singleton==null){
            //AppVentas app = new AppVentas();
            AppVentas app = new AppVentas();
            
            app.run();
            Singleton=app;
        }

        return Singleton;
    }
    
}
