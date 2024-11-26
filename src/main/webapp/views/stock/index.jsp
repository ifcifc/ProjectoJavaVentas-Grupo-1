<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>

<div class="container">
    <h2>Historial</h2>
    <form class="row h-center" style="gap:8px" accion="ventas" method="GET">
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
                <th>Fecha</th>
                <th>Articulo</th>
                <th>Stock</th>
                <th>Origen</th>
                <th>Accion</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="history_data" items="${history_stock}">
                <c:set var="history" value="${history_data.history}"></c:set>
                <c:set var="articulo" value="${history_data.articulo}"></c:set>
                <tr>
                    <td><c:out value="${history.fechaFormat}" /></td>
                <td><a href="stock?accion=show&id=${articulo.ID}" class="btn">${articulo.cod}</a></td>
                    <td style="font-weight: bold" class="${(history.value>0)? "stock-ok":"stock-sin-stock"}"><c:out value="${history.value}" /></td>
                    <td><c:out value='${history.meta.getClass().getName().equals("java.lang.String")?
                                        history.meta:
                                        "Venta"
                               }' /></td>
                    <td>
                        <c:choose>
                            <c:when test='${history.meta.getClass().getName()=="java.lang.String"}'>
                                <a class="btn btn-ver disable">Ver</a>
                            </c:when>
                            <c:otherwise>
                                <a href="ventas?accion=show&id=${history.meta.ID}" class="btn btn-ver">Ver</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table></div>
    <hr>
    <div class="botonera">
        <a onclick="window.history.back()" class="btn left">Atrás</a>
    </div>
</div>

<%@ include file="/views/layout/footer.jsp" %>
