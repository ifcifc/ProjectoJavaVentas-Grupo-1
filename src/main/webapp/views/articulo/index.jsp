<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="container">
    <h2>Articulos</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Código</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Precio</th>
                <th>En Stock</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="articulo" items="${articulos}">
                <tr>
                    <td><c:out value="${articulo.ID}" /></td>
                    <td><c:out value="${articulo.cod}" /></td>
                    <td><c:out value="${articulo.nombre}" /></td>
                    <td><c:out value="${articulo.descripcion}" /></td>
                    <td class="precio">
                       <a href="articulos?accion=precio&id=${articulo.ID}" class="btn"> <c:out value="${articulo.precio}" default="0" /> </a>
                    </td>
                    <td>
                       <a href="articulos?accion=stock&id=${articulo.ID}" class="btn"> <c:out value="${articulo.stock}" default="0" /> </a>
                    </td>

                    <td>
                        <c:choose>
                            <c:when test="${articulo.stock > 10}">
                                <span class="stock stock-ok">En Stock</span>
                            </c:when>
                            <c:when test="${articulo.stock > 0}">
                                <span class="stock stock-bajo">Stock Bajo</span>
                            </c:when>
                            <c:otherwise>
                                <span class="stock stock-sin-stock">Sin Stock</span>
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td class="actions">
                        <a href="articulos?accion=show&id=${articulo.ID}" class="btn btn-ver">Ver</a>
                        <a href="articulos?accion=edit&id=${articulo.ID}" class="btn btn-editar">Editar</a>
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
    <hr>
    <div class="botonera">
        <a href="." class="btn left">Atras</a>
        <a href="articulos?accion=create" class="btn btn-add">+ Añadir Artículo</a>
    </div>
</div>

<%@ include file="/views/layout/footer.jsp" %>
