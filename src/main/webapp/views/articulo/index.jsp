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
                    <td class="precio">$<c:out value="${articulo.precio}" /></td>
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
        <a href=".." class="btn left">Atras</a>
        <a href="articulos?accion=create" class="btn btn-add">+ Añadir Artículo</a>
    </div>
</div>

<%@ include file="/views/layout/footer.jsp" %>
