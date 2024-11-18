<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
<div class="container">
    <h2>Ingreso de dinero</h2>
    <form action="saldo?accion=Ingreso" method="POST">
        <div class="detail-row">
            <label class="label" for="monto">Monto:</label>
            <input type="number" id="monto" name="monto" min="0" step="0.01" value="0" required />
        </div>
        <div class="botonera">
            <button type="button" class="btn left" onclick="window.history.back()">Cancelar</button>
            <button type="submit" class="btn right">Guardar</button>
        </div>
    </form>
</div>
<%@ include file="/views/layout/footer.jsp" %>