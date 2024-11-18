<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!--
    param: accede a los parametros de la url
    Si se establece algun valor a isClient accedera como cliente
-->
<c:choose>
    <c:when test="${sessionScope.login.usuario.empleado}">
        <%@ include file="/views/menu/admin.jsp" %>
    </c:when>
    <c:otherwise>
        <%@ include file="/views/menu/client.jsp" %>
    </c:otherwise>
</c:choose>