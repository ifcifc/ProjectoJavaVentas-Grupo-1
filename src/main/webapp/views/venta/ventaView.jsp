<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
   <h2>Venta</h2>
   <div class="table-container"><table>
       <thead>
           <tr>
               <th>Nombre</th>
               <th>Código</th>
               <th>Precio</th>
               <th>Cantidad</th>
               <th>Subtotal</th>
               <th>Fecha</th>
               <th>Acciones</th>
           </tr>
       <tbody>
           <c:forEach var="venta" items="${ventas}">
               <tr>
                   <td><c:out value="${venta.articulo.nombre}" /></td>
                   <td><c:out value="${venta.articulo.cod}" /></td>
                   <td class="precio">$<c:out value="${venta.articulo.precio}" /></td>
                   <td class="precio"><c:out value="${venta.cantidad}" /></td>
                   <td class="precio">$<fmt:formatNumber value="${venta.articulo.precio * venta.cantidad}" type="number" maxFractionDigits="2" /></td>
                   <td><c:out value="${venta.fecha}" /></td>
                   <td class="actions">
                       <a href="ventas?accion=show&id=${venta.ID}" class="btn btn-ver" style="width: 100%">Ver</a>
                       <!--a href="ventas?accion=edit&id=${venta.ID}" class="btn btn-editar">Editar</a-->
                   </td>
               </tr>
           </c:forEach>
       </tbody>
   </table></div>
   <hr>
   <div class="botonera">
       <a href="${last}" class="btn left">Atras</a>
       <!--a href="ventas?accion=create" class="btn btn-add">+ Nueva Venta</a-->
   </div>
</div>

<%@ include file="/views/layout/footer.jsp" %>