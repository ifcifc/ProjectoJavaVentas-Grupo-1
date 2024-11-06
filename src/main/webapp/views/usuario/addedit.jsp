<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
    <div class="container">
        <h2>Formulario de Usuario</h2>
        <form action="${action}" method="${method}">
            <input type="hidden" name="id" value="${usuario.ID}">
            <input type="hidden" name="empleado" value="0"> 
            <div class="detail-row">
                <label class="label" for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="${usuario.nombre}" required />
            </div>

            <div class="detail-row">
                <label class="label" for="email">Email:</label>
                <input type="email" id="email" name="email" value="${usuario.email}" required />
            </div>

            <div class="detail-row">
                <label class="label" for="password">Contraseña:</label>
                <input type="password" id="password" name="password" value="${usuario.password}" required />
            </div>

            <div class="detail-row">
                <label class="label" for="empleado">Es Empleado:</label>
                <input type="checkbox" id="empleado" value="1" name="empleado" ${usuario.empleado ? "checked" : ""} />
            </div>

            <div class="botonera">
                <button type="button" class="btn left" onclick="window.history.back()">Cancelar</button>
                <button type="submit" class="btn right">Guardar</button>
            </div>
        </form>
    </div>
<%@ include file="/views/layout/footer.jsp" %>
