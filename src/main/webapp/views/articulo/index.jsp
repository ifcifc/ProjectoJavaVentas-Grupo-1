<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${mensaje}</h1>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Cod</th>
				<th>Nombre</th>
				<th>Descripcion</th>
				<th>Precio</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="articulo" items="${articulos}">
	            <tr>
                        <td><c:out value="${articulo.ID}" /></td>
                        <td><c:out value="${articulo.cod}" /></td>
                        <td><c:out value="${articulo.nombre}" /></td>
                        <td><c:out value="${articulo.descripcion}" /></td>
                        <td><c:out value="${articulo.precio}" /></td>
                        <td><a href="ArticulosController?accion=show&id=${articulo.ID}">ver</a></td>
                        <td>
                                <a href="ArticulosController?accion=edit&id=${articulo.ID}">editar</a>
                        </td>
	            </tr>
	        </c:forEach>
		</tbody>
	</table>
	
</body>
</html>