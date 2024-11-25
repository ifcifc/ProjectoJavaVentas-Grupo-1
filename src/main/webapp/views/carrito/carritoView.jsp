<%@page import="com.ventas.models.CarritoModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="container">
    <h2>Carrito</h2>
    <a href="saldo" class="btn left">Saldo: $<fmt:formatNumber value="${saldo}" type="number" maxFractionDigits="2" /></a>

    <div class="table-container"><table>
        <thead>
            <tr>
                <th>Código</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Precio</th>
                <th>En Stock</th>
                <th>En Carrito</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody>
            <c:set var="total" value="0" />
            <c:set var="enable_button" value="${carritos.size()>0}" />
            <c:forEach var="carrito" items="${carritos}">
                <c:set var="total" value="${total + carrito.cantidad * carrito.articulo.precio}" />
                <tr>
                    <td><c:out value="${carrito.articulo.cod}" /></td>
                    <td><c:out value="${carrito.articulo.nombre}" /></td>
                    <td><c:out value="${carrito.articulo.descripcion}" /></td>
                    <td class="precio">$<c:out value="${carrito.articulo.precio}" /></td>
                    <td ><c:out value="${carrito.articulo.stock}" default="0" /></td>
                    <td class="actions h-center">
                        <a onclick="onCarrito('${carrito.articulo.ID}', 1)" class="btn btn-add-aticulo left">+</a>
                        <span style="font-weight: bold" class="${(carrito.cantidad>carrito.articulo.stock)? "stock-sin-stock":""}" id="${carrito.articulo.ID}" data-max="<c:out value="${carrito.articulo.stock}" default="0" />"> 
                            <c:out value="${carrito.cantidad}" default="0" />
                        </span>
                        <a onclick="onCarrito('${carrito.articulo.ID}', -1)" class="btn btn-sub-aticulo right">-</a>
                    </td>

                    <td class="precio" >$<fmt:formatNumber value="${carrito.cantidad * carrito.articulo.precio}" type="number" maxFractionDigits="2" /></td>
                </tr>
                <c:if test="${carrito.cantidad>carrito.articulo.stock}">
                    <c:set var="enable_button" value="false" />
                </c:if>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>$<fmt:formatNumber value="${total}" type="number" maxFractionDigits="2" /></td>
            </tr>
        </tbody>
    </table></div>
    <hr>
    <div class="botonera">
        <a onclick="window.history.back(); " class="btn left">Atras</a>
        <c:if test="${enable_button}">
            <form action="ventas?accion=venta" method="post">
                <a onclick="this.parentElement.submit()" class="btn right">Comprar</a>
            </form>
        </c:if>

    </div>
</div>

<form id="form_id" action="carrito?accion=carrito" method="POST" hidden>
    <input id="form_data_id" name="id" type="hidden">
    <input id="form_data_cnt" name="cantidad" type="hidden">
    <input name="from" value="carrito" type="hidden">
</form>

<script>
    var timeOut = -1;
    var id_articulo = "";
    var cantidad = 0;
    var onUpdate = false;

    //Envia los datos modificados al servidor 
    function update() {
        if (onUpdate)
            return;
        onUpdate = true;

        //Desactivo todos los botones
        for (var item in document.getElementsByClassName("btn")) {
            item.disabled = true;
        }

        document.getElementById("form_data_id").value = id_articulo;
        document.getElementById("form_data_cnt").value = cantidad;
        document.getElementById("form_id").submit();
    }


    function onCarrito(id, cnt) {
        //Si la id del articulo no coincide no permito cambiar los valores
        if (id_articulo !== "" && id_articulo !== id)
            return;

        //Detengo el timeout para que no envie los datos
        if (timeOut !== -1)
            clearTimeout(timeOut);

        //Obtengo el elemento que contiene los datos del carrito
        let cnt_element = document.getElementById(id);

        let text = cnt_element.textContent;

        //Obtengo el limite de stock del articulo
        let max = parseInt(cnt_element.dataset.max);

        if (max === 0) {
            cnt_element.textContent = 0;
        }

        //Establesco el id del articulo a trabajar
        id_articulo = id;

        //Sumo el incremento a la cantidad, 
        //y le ago un clamp a cantidad para q no supere el minimo ni el maximo permitido
        cantidad = parseInt(text) + cnt;
        cantidad = Math.min(Math.max(cantidad, 0), max);

        //Actualizo el elemento con la cantidad
        cnt_element.textContent = cantidad;

        //Inicio el temporalizador para actualizar la pagina
        timeOut = setTimeout(update, 600);
    }


</script>


<%@ include file="/views/layout/footer.jsp" %>
