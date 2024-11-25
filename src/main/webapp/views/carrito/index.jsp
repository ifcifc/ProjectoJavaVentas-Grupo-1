<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
    <h2>Carrito de Compras</h2>
    <div class="table-container"><table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Código</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Subtotal</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="carrito" items="${carritos}">
                <tr>
                    <td><c:out value="${carrito.ID}" /></td>
                    <td><c:out value="${carrito.articulo.nombre}" /></td>
                    <td><c:out value="${carrito.articulo.cod}" /></td>
                    <td>$<c:out value="${carrito.articulo.precio}" /></td>
                    <td><c:out value="${carrito.cantidad}" /></td>
                    <td>$<c:out value="${carrito.articulo.precio * carrito.cantidad}" /></td>
                    <td class="actions">
                        <a href="carrito?accion=show&id=${carrito.ID}" class="btn btn-ver">Ver</a>
                        <a href="carrito?accion=edit&id=${carrito.ID}" class="btn btn-editar">Editar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="5" style="text-align: right;"><strong>Total:</strong></td>
                <td class="precio">$${total}</td>
                <td></td>
            </tr>
        </tfoot>
    </table></div>
    <hr>
    <div class="botonera">
        <a href="." class="btn left">Atras</a>
    </div>
</div>

<script>
    document.getElementById("id_select").addEventListener("change", ()=>{
        
        document.getElementById("id_form").submit();
    });
</script> 
                
<%@ include file="/views/layout/footer.jsp" %>