<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Articulo - Detalle</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h2>Detalles del Artículo</h2>

        <div class="detail-row">
            <span class="label">ID:</span>
            <span>${articulo.ID}</span>
        </div>

        <div class="detail-row">
            <span class="label">Código:</span>
            <span>${articulo.cod}</span>
        </div>

        <div class="detail-row">
            <span class="label">Nombre:</span>
            <span>${articulo.nombre}</span>
        </div>

        <div class="detail-row">
            <span class="label">Descripción:</span>
            <span>${articulo.descripcion}</span>
        </div>

        <div class="detail-row">
            <span class="label">Precio:</span>
            <span>$${articulo.precio}</span>
        </div>

        <div class="botonera">
            <a href="articulos" class="btn left">← Volver</a>
            <a href="articulos?accion=edit&id=${articulo.ID}" class="btn">Modificar</a>
            <a href="" class="btn">Eliminar</a>
        </div>
    </div>
</body>
</html>