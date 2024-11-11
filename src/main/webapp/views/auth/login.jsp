<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
    <div class="container">
        <h2>Iniciar Sesión</h2>
        <form action="" method="POST">
            
            <div class="detail-row">
                <label class="label" for="email">Email:</label>
                <input type="email" id="email" name="email" required />
            </div>

            <div class="detail-row">
                <label class="label" for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required />
            </div>

            <div class="botonera">
                <button type="submit" class="btn right">Iniciar Sesión</button>
            </div>

            <p class="mensaje">¿No tienes una cuenta? <a href="?accion=registro">Regístrate aquí</a></p>
        </form>
    </div>
<%@ include file="/views/layout/footer.jsp" %>
