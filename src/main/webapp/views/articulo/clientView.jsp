<%@page import="com.ventas.models.CarritoModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="container">
    <h2>Articulos Tienda</h2>
    <a href="saldo" class="btn left">Saldo: $<fmt:formatNumber value="${saldo}" type="number" maxFractionDigits="2" /></a>
    <table>
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
            <c:forEach var="articulo" items="${articulos}">
                <c:set var="carrito_cantidad" value="${carrito[articulo.ID].cantidad}" />
                <c:set var="total" value="${total + carrito_cantidad * articulo.precio}" />
                <tr>
                    <td><c:out value="${articulo.cod}" /></td>
                    <td><c:out value="${articulo.nombre}" /></td>
                    <td><c:out value="${articulo.descripcion}" /></td>
                    <td class="precio">$<c:out value="${articulo.precio}" /></td>
                    <td ><c:out value="${articulo.stock}" default="0" /></td>
                    <td class="actions h-center">
                        <a onclick="onCarrito('${articulo.ID}', 1)" class="btn btn-add-aticulo left">+</a>
                        <span style="font-weight: bold" class="${(carrito_cantidad>articulo.stock)? "stock-sin-stock":""}" id="${articulo.ID}" data-max="<c:out value="${articulo.stock}" default="0" />"> 
                            <c:out value="${carrito_cantidad}" default="0" />
                        </span>
                        <a onclick="onCarrito('${articulo.ID}', -1)" class="btn btn-sub-aticulo right">-</a>
                    </td>

                    <td class="precio" >$<c:out value="${carrito_cantidad * articulo.precio}" default="0" /></td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>$${total}</td>
            </tr>
        </tbody>
    </table>
    <hr>
    <div class="botonera">
        <a href=".." class="btn left">Atras</a>
        <c:if test="${total>0}">
            <a href="carrito?accion=carrito" class="btn right">Comprar</a>
        </c:if>
    </div>
</div>

<form id="form_id" action="carrito?accion=carrito" method="POST" hidden>
    <input id="form_data_id" name="id" type="hidden">
    <input id="form_data_cnt" name="cantidad" type="hidden">
    <input name="from" value="articulos" type="hidden">
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

        if (max === 0 && cnt>0) {
            alert("No hay stock de este articulo");
            return;
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
        timeOut = setTimeout(update, 1000);
    }


</script>


<%@ include file="/views/layout/footer.jsp" %>
