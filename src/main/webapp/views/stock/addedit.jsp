<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
    <h2>Formulario de Stock</h2>
    <form action="${action}" method="${method}">
            <input type="hidden" name="id" value="${stock.ID}">

        <div class="detail-row">
            <label class="label" for="articulo">Articulo:</label>
            <select id="articulo" name="articulo" value="${stock.cantidad}" />
                <c:forEach var="articulo" items="${articulos}">
                    <option value="${articulo.ID}"
                            <c:if test="${articulo.ID==stock.articulo.ID}">selected</c:if>
                            >${articulo.nombre}</option>
                </c:forEach>
            </select>
                
        </div>

        <div class="detail-row">
            <label class="label" for="cantidad">Código:</label>
            <input type="number" id="cantidad" name="cantidad" value="${stock.cantidad}" />
        </div>

        <div class="botonera">
            <button type="button" class="btn left" onclick="window.history.back()">Cancelar</button>
            <button type="submit" class="btn right">Guardar</button>
        </div>
    </form>
</div>
<%@ include file="/views/layout/footer.jsp" %>