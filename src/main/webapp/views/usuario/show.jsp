<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
    <div class="container">
        <h2>Detalles del Usuario</h2>

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

        <div class="detail-row">
            <span class="label">Es Empleado:</span>
            <span>${usuario.empleado}</span>
        </div>

        <div class="botonera">
            <a href="usuarios" class="btn left">Volver</a>
            <a href="?accion=edit&id=${usuario.ID}" class="btn">Modificar</a>
            <a href="?accion=delete&id=${usuario.ID}" class="btn">Eliminar</a>
        </div>
    </div>
<%@ include file="/views/layout/footer.jsp" %>
