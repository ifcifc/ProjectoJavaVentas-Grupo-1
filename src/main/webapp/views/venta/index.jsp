<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Codigo</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Fecha</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="venta" items="${ventas}">
                <tr>
                    <td><c:out value="${venta.ID}" /></td>
                    <td><c:out value="${venta.articulo.nombre}" /></td>
                    <td><c:out value="${venta.articulo.cod}" /></td>
                    <td><c:out value="${venta.articulo.precio}" /></td>
                    <td><c:out value="${venta.cantidad}" /></td>
                    <td><c:out value="${venta.fecha}" /></td>
                    <td><a href="VentaController?accion=show&id=${venta.ID}">ver</a></td>
                    <td><a href="VentaController?accion=edit&id=${venta.ID}">editar</a></td>
                </tr>
            </c:forEach>
            </tbody>
    </table>
</body>
</html>