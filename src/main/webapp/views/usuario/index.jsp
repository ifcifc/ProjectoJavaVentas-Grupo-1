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
                    <th>Email</th>
                    <th>Es empleado</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="usuario" items="${usuarios}">
                <tr>
                    <td><c:out value="${usuario.ID}" /></td>
                    <td><c:out value="${usuario.nombre}" /></td>
                    <td><c:out value="${usuario.email}" /></td>
                    <td><c:out value="${usuario.empleado}" /></td>
                    <td><a href="UsuarioController?accion=show&id=${usuario.ID}">ver</a></td>
                    <td><a href="UsuarioController?accion=edit&id=${usuario.ID}">editar</a></td>
                </tr>
            </c:forEach>
            </tbody>
    </table>
	
</body>
</html>