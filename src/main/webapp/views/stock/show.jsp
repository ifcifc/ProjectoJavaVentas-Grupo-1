<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
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

    <div class="detail-row row h-center">
        <span class="label">Descripción:</span>
        <span class="left">${articulo.descripcion}</span>
        <a href="articulos?accion=historialDescripcion&id=${articulo.ID}" class="btn right">Historial</a>
    </div>

    <div class="detail-row row h-center">
        <span class="label">Precio:</span>
        <span class="left">$${articulo.precio}</span>
        <a href="articulos?accion=historialPrecio&id=${articulo.ID}" class="btn right">Historial</a>
    </div>
    
    <div class="detail-row row h-center">
        <span class="label">Stock:</span>
        <span class="left">${articulo.stock}</span>
        <a href="articulos?accion=historial&id=${articulo.ID}" class="btn right">Historial</a>
    </div>


    <div class="botonera">
        <a onclick="window.history.back()" class="btn left">Volver</a>
    </div>
</div>
<%@ include file="/views/layout/footer.jsp" %>
