<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>

<div class="container">
    <h2>Lista de Movimientos</h2>
    <table>
        <thead>
            <tr>
                <th>Monto</th>
                <th>Fecha</th>
                <th>Origen</th>
                <th>Destino</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="movimiento" items="${movimientos}">
                <tr>
                    <td>$<c:out value="${movimiento.monto}" /></td>
                    <td><c:out value="${movimiento.fecha}" /></td>
                    <td><c:out value="${movimiento.from.nombre}" default="ingreso" /></td>
                    <td><c:out value="${movimiento.to.nombre}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <span><b>Saldo:</b> $ ${saldo}</span>

    <hr>
    <div class="botonera" style="gap:1rem">
        <a href="." class="btn left">Atrás</a>
        <a href="saldo?accion=ingreso" class="btn btn-add">Ingresar dinero</a>
        <a href="saldo?accion=ingreso" class="btn btn-add">Transferir</a>
    </div>
</div>

<%@ include file="/views/layout/footer.jsp" %>
