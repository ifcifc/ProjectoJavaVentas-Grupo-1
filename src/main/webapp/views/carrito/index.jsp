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
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="carrito" items="${carritos}">
                <tr>
                    <td><c:out value="${carrito.ID}" /></td>
                    <td><c:out value="${carrito.articulo.nombre}" /></td>
                    <td><c:out value="${carrito.articulo.cod}" /></td>
                    <td><c:out value="${carrito.articulo.precio}" /></td>
                    <td><c:out value="${carrito.cantidad}" /></td>
                    <td><a href="CarritoController?accion=show&id=${carrito.ID}">ver</a></td>
                    <td><a href="CarritoController?accion=edit&id=${carrito.ID}">editar</a></td>
                </tr>
            </c:forEach>
            </tbody>
    </table>
</body>
</html>