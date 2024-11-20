<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
    <div class="container">
        <h2>Transferencia</h2>
        <form action="saldo?accion=transferencia" method="POST">
            
            <div class="detail-row">
                <label class="label" for="id_usuario">Usuario:</label>
                <select id="id_usuario" name="id_usuario" required>
                    <c:forEach var="usuario" items="${usuarios}">
                        <option value="${usuario.ID}">${usuario.nombre}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="detail-row">
                <label class="label" for="monto">Monto:</label>
                <input type="number" id="monto" name="monto" step="0.01" required />
            </div>

            <div class="botonera">
                <button type="button" class="btn left" onclick="window.history.back()">Cancelar</button>
                <button type="submit" class="btn right">Guardar</button>
            </div>
        </form>
    </div>
<%@ include file="/views/layout/footer.jsp" %>
