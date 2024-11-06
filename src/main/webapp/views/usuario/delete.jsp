<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
    <div class="container">
        <h2>¿Está seguro que desea eliminar este usuario?</h2>

        <div class="detail-row">
            <span class="label">ID:</span>
            <span>${usuario.ID}</span>
        </div>

        <div class="detail-row">
            <span class="label">Nombre:</span>
            <span>${usuario.nombre}</span>
        </div>

        <div class="detail-row">
            <span class="label">Email:</span>
            <span>${usuario.email}</span>
        </div>

        <form action="" method="POST" class="botonera">
            <input name="id" type="hidden" value="${usuario.ID}">
            <a href="?accion=show&id=${usuario.ID}" class="btn left">Volver</a>
            <input type="submit" class="btn" value="Eliminar">
        </form>
    </div>
<%@ include file="/views/layout/footer.jsp" %>
