<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>

<div class="container">
    <h2>Lista de Movimientos</h2>
    <form class="row h-center" style="gap:8px" accion="saldo" method="GET">
        <input type="hidden" value="all" name="accion">
        <div>
            <label for="fecha_from"><div class="label-espacio" style="width: 4rem;">Desde:</div></label>
            <input type="date" id="fecha_from" name="fecha_from" value="${fecha_from}" required>
        </div>
        <div>
            <label for="fecha_to">Hasta:</label>
            <input type="date" id="fecha_to" name="fecha_to" value="${fecha_to}" required>
        </div>
        
        
        <div class="right">
            <input type="submit" onclick="document.getElementById('fecha_to').remove();document.getElementById('fecha_from').remove();" class="right btn btn-filter" value="Limpiar">
            <input type="submit" class="right btn btn-filter" value="Filtrar">
        </div>
    </form>
    <hr>
    <div class="table-container"><table>
            <thead>
                <tr>
                    <th>Monto</th>
                    <th>Fecha</th>
                    <th>Origen</th>
                    <th>Destino</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="movimiento" items="${movimientos}">
                    <c:set var="controller" value="?accion=show&id=${movimiento.ID}" />
                    <tr>
                        <td style="font-weight: bold" class="${movimiento.venta? "stock-ok":(movimiento.ingreso?"stock-bajo":"")}">$<fmt:formatNumber value="${movimiento.monto}" type="number" maxFractionDigits="2" /></td>
                        <td><c:out value="${movimiento.fechaFormat}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${movimiento.venta}">
                                    Venta
                                    <c:set var="controller" value="ventas?accion=view&id=${movimiento.from.ID}&last=all"/>
                                </c:when>
                                <c:when test="${movimiento.transferencia}">
                                    <c:out value="${movimiento.from.nombre}" />
                                </c:when>
                                <c:otherwise>
                                    Ingreso
                                </c:otherwise> 
                            </c:choose>
                        </td>
                        <td><c:out value="${movimiento.to.nombre}" /></td>
                        <td class="actions">
                            <a href="${controller}" class="btn btn-ver" style="width: 100%">Ver</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table></div>
    <br>
    <hr>
    <div class="botonera" style="gap:1rem">
        <a href="." class="btn left">Atrás</a>
    </div>
</div>

<%@ include file="/views/layout/footer.jsp" %>
