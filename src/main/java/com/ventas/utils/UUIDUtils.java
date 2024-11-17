package com.ventas.utils;

import java.util.Random;
import java.util.UUID;

public class UUIDUtils {

    private static Random random;

    //Genera UUID, para evitar que cada vez que el tomcat se recargue cambie las UUID
    public static UUID randomUUID() {
        if (random == null) {
            random = new Random(1234L);
        }
        return new UUID(random.nextLong(), random.nextLong());
    }
    
    public static UUID fromString(String uuid){
        try{
            return UUID.fromString(uuid);
        }catch(Exception e){
            return null;
        }
    }
}
