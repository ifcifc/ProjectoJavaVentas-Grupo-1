<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/views/layout/header.jsp" %>

<c:choose>
    <c:when test="${not empty sessionScope.login}">
        <%@ include file="/views/menu/menu.jsp" %>
    </c:when>
    <c:otherwise>
        <%@ include file="/views/auth/index.jsp" %>
    </c:otherwise>
</c:choose>
<%@ include file="/views/layout/footer.jsp" %>