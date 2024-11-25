<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
    <div class="container">
        <h2>Detalles del movimiento</h2>
        <div class="detail-row">
            <span class="label">Origen:</span>
            <span><c:out value="${movimiento.from.nombre}" default="Ingreso" /></span>
        </div>
        <div class="detail-row">
            <span class="label">Destino:</span>
            <span>${movimiento.to.nombre}</span>
        </div>
        <div class="detail-row">
            <span class="label">Fecha:</span>
            <span>${movimiento.fecha}</span>
        </div>

        <div class="detail-row">
            <span class="label">Monto:</span>
            <span class="${(sessionScope.login.usuario.equals(movimiento.from) || movimiento.venta)? "stock-sin-stock":""}">$<c:out value="${movimiento.monto}" /></span>
        </div>

        <div class="botonera">
            <a onclick="window.history.back()" class="btn left">Volver</a>
        </div>
    </div>
<%@ include file="/views/layout/footer.jsp" %>
