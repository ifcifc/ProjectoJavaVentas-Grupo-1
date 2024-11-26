<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
    <div class="container">
        <h2>¿Está seguro que desea eliminar esta venta?</h2>

        <div class="detail-row">
            <span class="label">ID:</span>
            <span>${venta.ID}</span>
        </div>

        <div class="detail-row">
            <span class="label">Fecha:</span>
            <span>${venta.fechaFormat}</span>
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

        <form action="" method="POST" class="botonera">
            <input name="id" type="hidden" value="${venta.ID}">
            <a href="?accion=show&id=${venta.ID}" class="btn left">Volver</a>
            <input type="submit" class="btn" value="Eliminar">
        </form>
    </div>
<%@ include file="/views/layout/footer.jsp" %>
