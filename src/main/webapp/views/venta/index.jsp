<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
   <h2>Ventas</h2>
   <table>
       <thead>
           <tr>
               <th>ID</th>
               <th>Fecha</th>
               <th>Usuario</th>
               <th>Total</th>
               <th>Acciones</th>
           </tr>
       </thead>
       <tbody>
           <c:forEach var="ventag" items="${ventasGroup}">
               <tr>
                   <td><c:out value="${ventag.ID}" /></td>
                   <td><c:out value="${ventag.fecha}" /></td>
                   <td><c:out value="${ventag.usuario.nombre}" /></td>
                   <td><c:out value="$${ventag.total}" /></td>
                   <td class="actions">
                       <a href="ventas?accion=view&id=${ventag.ID}" class="btn btn-ver" style="width: 100%">Ver</a>
                   </td>
               </tr>
           </c:forEach>
       </tbody>
   </table>
   <hr>
   <div class="botonera">
       <a href="." class="btn left">Atras</a>
       <!--a href="ventas?accion=create" class="btn btn-add">+ Nueva Venta</a-->
   </div>
</div>

<%@ include file="/views/layout/footer.jsp" %>