<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
    <div class="container">
        <h2>Esta seguro que desea eliminar este stock?</h2>

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

        <form action="" method="POST" class="botonera">
            <input name="id" type="hidden" value="${stock.ID}">
            <a href="?accion=show&id=${stock.ID}" class="btn left">Volver</a>
            <input type="submit" class="btn" value="Eliminar">
        </form>
    </div>
<%@ include file="/views/layout/footer.jsp" %>