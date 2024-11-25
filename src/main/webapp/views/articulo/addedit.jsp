<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
    <div class="container">
        <h2>Formulario de Artículo</h2>
        <form action="" method="POST">
            <input type="hidden" name="id" value="${articulo.ID}">
            <div class="detail-row">
                <label class="label" for="cod">Código:</label>
                <input type="number" id="cod" name="cod" value="${articulo.cod}" />
            </div>

            <div class="detail-row">
                <label class="label" for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="${articulo.nombre}" required />
            </div>

            <div class="detail-row">
                <label class="label" for="descripcion">Descripción:</label>
                <textarea id="descripcion" name="descripcion">${articulo.descripcion}</textarea>
            </div>

            <div class="detail-row">
                <label class="label" for="precio">Precio:</label>
                <input type="number" id="precio" name="precio" step="0.01" value="${articulo.precio}" required />
            </div>
            
            <div class="detail-row">
                <label class="label" for="stock">Stock:</label>
                <input type="number" id="stock" name="stock" step="0" value="${articulo.stock}" required />
            </div>

            <div class="botonera">
                <button type="button" class="btn left" onclick="window.history.back()">Cancelar</button>
                <button type="submit" class="btn right">Guardar</button>
            </div>
        </form>
    </div>
<%@ include file="/views/layout/footer.jsp" %>
