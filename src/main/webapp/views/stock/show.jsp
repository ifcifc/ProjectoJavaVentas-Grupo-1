<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
    <div class="container">
        <h2>Detalles del Stock</h2>

        <div class="detail-row">
            <span class="label">ID:</span>
            <span>${stock.ID}</span>
        </div>

        <div class="detail-row">
            <span class="label">Artículo:</span>
            <span>${stock.articulo.nombre}</span>
        </div>

        <div class="detail-row">
            <span class="label">Código:</span>
            <span>${stock.articulo.cod}</span>
        </div>

        <div class="detail-row">
            <span class="label">Cantidad:</span>
            <span>${stock.cantidad}</span>
        </div>

        <div class="botonera">
            <a href="stock" class="btn left">Volver</a>
            <a href="?accion=edit&id=${stock.ID}" class="btn">Modificar</a>
            <a href="?accion=delete&id=${stock.ID}" class="btn">Eliminar</a>
        </div>
    </div>
<%@ include file="/views/layout/footer.jsp" %>
