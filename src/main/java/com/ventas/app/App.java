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
            AppTest app = new AppTest();
            
            app.run();
            Singleton=app;
        }

        return Singleton;
    }
    
}
