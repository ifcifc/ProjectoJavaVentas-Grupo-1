<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Carrito App</title>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <c:if test="${not empty sessionScope.login}">
            <nav>
                <c:if test="${sessionScope.login.usuario.empleado}">
                    <a href="." class="btn">Admin</a>
                </c:if>
                <a href=".?isClient=1" class="btn">Cliente</a>
                <a href="auth?accion=logout" class="btn right">Cerrar Sesion</a>
            </nav>
        </c:if>