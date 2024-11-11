<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
    <div class="container">
        <h2>Registro de Usuario</h2>
        <form action="" method="POST">

            <div class="detail-row">
                <label class="label" for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required />
            </div>

            <div class="detail-row">
                <label class="label" for="email">Email:</label>
                <input type="email" id="email" name="email" required />
            </div>

            <div class="detail-row">
                <label class="label" for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required />
            </div>

            <div class="botonera">
                <button type="button" class="btn left" onclick="window.history.back()">Cancelar</button>
                <button type="submit" class="btn right">Registrar</button>
            </div>

            <p class="mensaje">¿Ya tienes una cuenta? <a href="?accion=login">Inicia sesión aquí</a></p>
        </form>
    </div>
<%@ include file="/views/layout/footer.jsp" %>
