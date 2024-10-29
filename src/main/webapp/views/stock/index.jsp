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
                    <th>Articulo</th>
                    <th>Cantidad</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                    <c:forEach var="stock" items="${stockList}">
                <tr>
                    <td><c:out value="${stock.ID}" /></td>
                    <td><c:out value="${stock.articulo.nombre}" /></td>
                    <td><c:out value="${stock.cantidad}" /></td>
                    <td><a href="StockController?accion=show&id=${stock.ID}">ver</a></td>
                    <td><a href="StockController?accion=edit&id=${stock.ID}">editar</a></td>
                </tr>
            </c:forEach>
            </tbody>
    </table>
	
</body>
</html>