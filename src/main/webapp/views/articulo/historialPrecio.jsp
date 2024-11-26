<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>

<div class="container">
    <h2>Historial</h2>
    <div class="table-container"><table>
        <thead>
            <tr>
                <th>Fecha</th>
                <th>Precio</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="history" items="${history_precio}">
                <tr>
                    <td><c:out value="${history.fechaFormat}" /></td>
                    <td><c:out value="${history.value}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table></div>
    <hr>
    <div class="botonera">
        <a onclick="window.history.back()" class="btn left">Atrás</a>
    </div>
</div>

<%@ include file="/views/layout/footer.jsp" %>
