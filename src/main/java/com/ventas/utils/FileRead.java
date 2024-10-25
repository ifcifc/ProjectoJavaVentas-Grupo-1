package com.ventas.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class FileRead {
    public static String read(String file) throws IOException, URISyntaxException {
        ClassLoader classLoader = FileRead.class.getClassLoader();
        java.net.URI uri = Objects.requireNonNull(classLoader.getResource(file)).toURI();
        return new String(Files.readAllBytes(Paths.get(uri)));
    }
}
