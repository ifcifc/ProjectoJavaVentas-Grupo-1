<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
    <h2>Formulario de Stock</h2>
    <form action="" method="post">
        <input type="hidden" name="id" value="${articulo.ID}">
        <div class="detail-row">
            <div class="row h-center">
                <label class="label" for="stock">Stock:</label>
                <a href="?accion=historial&id=${articulo.ID}" class="btn right">Historial</a>
            </div>
            <input type="number" id="stock" min="0" name="stock" value="${articulo.stock}" />
        </div>

        <div class="botonera">
            <button type="button" class="btn left" onclick="window.history.back()">Cancelar</button>
            <button type="submit" value="sub" name="action" class="btn right">Quitar</button>
            <button type="submit" value="add" name="action" class="btn right">Añadir</button>
            <button type="submit" value="set" name="action" class="btn right">Establecer</button>
        </div>
    </form>
</div>
<%@ include file="/views/layout/footer.jsp" %>