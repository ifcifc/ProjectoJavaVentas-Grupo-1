<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>

<div class="container">
    <h2>Historial</h2>
    <table>
        <thead>
            <tr>
                <th>Fecha</th>
                <th>Stock</th>
                <th>Origen</th>
                <th>Accion</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="history" items="${history_stock}">
                <tr>

                    <td><c:out value="${history.fecha}" /></td>
                    <td style="font-weight: bold" class="${(history.value>0)? "stock-ok":"stock-sin-stock"}"><c:out value="${history.value}" /></td>
                    <td><c:out value='${history.meta.getClass().getName().equals("java.lang.String")?
                                        history.meta:
                                        "Venta"
                               }' /></td>
                    <td>
                        <c:choose>
                            <c:when test='${history.meta.getClass().getName()=="java.lang.String"}'>
                                <a class="btn btn-ver disable">Ver</a>
                            </c:when>
                            <c:otherwise>
                                <a href="ventas?accion=show&id=${history.meta.ID}" class="btn btn-ver">Ver</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <hr>
    <div class="botonera">
        <a onclick="window.history.back()" class="btn left">Atrás</a>
    </div>
</div>

<%@ include file="/views/layout/footer.jsp" %>
