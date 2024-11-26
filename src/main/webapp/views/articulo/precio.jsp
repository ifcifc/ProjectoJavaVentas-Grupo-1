<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
    <h2>Formulario de Precio</h2>
    <form action="" method="post">
        <input type="hidden" name="id" value="${articulo.ID}">
        <div class="detail-row">
            <div class="row h-center">
                <label class="label" for="precio">Precio:</label>
                <a href="?accion=historialPrecio&id=${articulo.ID}" class="btn right">Historial</a>
            </div>
            <input type="number" id="precio" name="precio" step="0.01" value="${articulo.precio}" required />
        </div>

        <div class="botonera">
            <button type="button" class="btn left" onclick="window.history.back()">Cancelar</button>
            <button type="submit" class="btn right">Guardar</button>
        </div>
    </form>
</div>
<%@ include file="/views/layout/footer.jsp" %>