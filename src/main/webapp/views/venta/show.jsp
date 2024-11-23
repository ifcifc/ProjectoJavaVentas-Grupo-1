<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
    <div class="container">
        <h2>Detalles de la Venta</h2>

        <div class="detail-row">
            <span class="label">ID:</span>
            <span>${venta.ID}</span>
        </div>

        <div class="detail-row">
            <span class="label">Fecha:</span>
            <span>${venta.fecha}</span>
        </div>

        <div class="detail-row">
            <span class="label">Artículo:</span>
            <span>${venta.articulo.nombre}</span>
        </div>

        <div class="detail-row">
            <span class="label">Cantidad:</span>
            <span>${venta.cantidad}</span>
        </div>

        <div class="detail-row">
            <span class="label">Usuario:</span>
            <span>${venta.usuario.nombre}</span>
        </div>

        <div class="botonera">
            <a href="/ventas" class="btn left">Volver</a>
            <a href="?accion=edit&id=${venta.ID}" class="btn">Modificar</a>
            <a href="?accion=delete&id=${venta.ID}" class="btn">Eliminar</a>
        </div>
    </div>
<%@ include file="/views/layout/footer.jsp" %>
