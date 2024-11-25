<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
    <div class="container">
        <h2>Formulario del Carrito</h2>
        <form action="" method="POST">
            <input type="hidden" name="id" value="${carrito.ID}">

            <div class="detail-row">
                <label class="label" for="articulo">Artículo:</label>
                <select id="articulo" name="articulo" required>
                    <c:forEach var="articulo" items="${articulos}">
                        <option value="${articulo.ID}" ${carrito.articulo.ID == articulo.ID ? "selected" : ""}>${articulo.nombre}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="detail-row">
                <label class="label" for="cantidad">Cantidad:</label>
                <input type="number" id="cantidad" name="cantidad" value="${carrito.cantidad}" required />
            </div>

            <div class="botonera">
                <button type="button" class="btn left" onclick="window.history.back()">Cancelar</button>
                <button type="submit" class="btn right">Guardar</button>
            </div>
        </form>
    </div>
<%@ include file="/views/layout/footer.jsp" %>
