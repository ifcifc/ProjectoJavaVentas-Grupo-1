<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
    <div class="container">
        <h2>Detalles del Carrito</h2>

        <div class="detail-row">
            <span class="label">ID:</span>
            <span>${carrito.ID}</span>
        </div>

        <div class="detail-row">
            <span class="label">Artículo:</span>
            <span>${carrito.articulo.nombre}</span>
        </div>

        <div class="detail-row">
            <span class="label">Cantidad:</span>
            <span>${carrito.cantidad}</span>
        </div>

        <div class="detail-row">
            <span class="label">Usuario:</span>
            <span>${carrito.usuario.nombre} - ${carrito.usuario.email}</span>
        </div>

        <div class="botonera">
            <a href="carrito" class="btn left">Volver</a>
            <a href="?accion=edit&id=${carrito.ID}" class="btn">Modificar</a>
            <a href="?accion=delete&id=${carrito.ID}" class="btn">Eliminar</a>
        </div>
    </div>
<%@ include file="/views/layout/footer.jsp" %>
