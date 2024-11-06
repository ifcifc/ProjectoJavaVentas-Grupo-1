<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
    <div class="container">
        <h2>Esta seguro que desea eliminar este artículo?</h2>

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

        <form action="" method="POST" class="botonera">
            <input name="id" type="hidden" value="${articulo.ID}">
            <a href="?accion=show&id=${articulo.ID}" class="btn left">Volver</a>
            <input type="submit" class="btn" value="Eliminar">
        </form>
    </div>
<%@ include file="/views/layout/footer.jsp" %>
