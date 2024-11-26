<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
    <h2>Ventas</h2>
    <form class="row h-center" style="gap:8px" accion="ventas" method="GET">
        <div>
            <label for="fecha_from"><div class="label-espacio" style="width: 4rem;">Desde:</div></label>
            <input type="date" id="fecha_from" name="fecha_from" value="${fecha_from}" required>
        </div>
        <div>
            <label for="fecha_to">Hasta:</label>
            <input type="date" id="fecha_to" name="fecha_to" value="${fecha_to}" required>
        </div>
        <input type="submit" class="right btn btn-filter" value="Filtrar">
    </form>
    <hr>
    <div class="table-container"><table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Fecha</th>
                    <th>Usuario</th>
                    <th>Total</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="ventag" items="${ventasGroup}">
                    <tr>
                        <td><c:out value="${ventag.ID}" /></td>
                        <td><c:out value="${ventag.fechaFormat}" /></td>
                        <td><c:out value="${ventag.usuario.nombre}" /></td>
                        <td><c:out value="$${ventag.total}" /></td>
                        <td class="actions">
                            <a href="ventas?accion=view&id=${ventag.ID}&last=ventas" class="btn btn-ver" style="width: 100%">Ver</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table></div>
    <hr>
    <div class="botonera">
        <a href="." class="btn left">Atras</a>
        <!--a href="ventas?accion=create" class="btn btn-add">+ Nueva Venta</a-->
    </div>
</div>

<%@ include file="/views/layout/footer.jsp" %>