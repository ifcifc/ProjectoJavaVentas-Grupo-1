<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
    <h2>Control de Stock</h2>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Artículo</th>
                <th>Código</th>
                <th>Cantidad</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="stock" items="${stockList}">
                <tr>
                    <td><c:out value="${stock.ID}" /></td>
                    <td><c:out value="${stock.articulo.nombre}" /></td>
                    <td><c:out value="${stock.articulo.cod}" /></td>
                    <td class="precio"><c:out value="${stock.cantidad}" /></td>
                    <td>
                        <c:choose>
                            <c:when test="${stock.cantidad > 10}">
                                <span class="stock stock-ok">En Stock</span>
                            </c:when>
                            <c:when test="${stock.cantidad > 0}">
                                <span class="stock stock-bajo">Stock Bajo</span>
                            </c:when>
                            <c:otherwise>
                                <span class="stock stock-sin-stock">Sin Stock</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="actions">
                        <a href="stock?accion=show&id=${stock.ID}" class="btn btn-ver">Ver</a>
                        <a href="stock?accion=edit&id=${stock.ID}" class="btn btn-editar">Editar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <hr>
    <div class="botonera">
        <a href=".." class="btn left">Atras</a>
        <a href="StockController?accion=create" class="btn btn-add">+ Añadir Stock</a>
    </div>
</div>

<%@ include file="/views/layout/footer.jsp" %>