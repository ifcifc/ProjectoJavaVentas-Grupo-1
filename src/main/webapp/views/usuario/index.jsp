<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@ include file="/views/layout/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
   <h2>Usuarios</h2>
   <table>
       <thead>
           <tr>
               <th>ID</th>
               <th>Nombre</th>
               <th>Email</th>
               <th>Tipo</th>
               <th>Acciones</th>
           </tr>
       </thead>
       <tbody>
           <c:forEach var="usuario" items="${usuarios}">
               <tr>
                   <td><c:out value="${usuario.ID}" /></td>
                   <td><c:out value="${usuario.nombre}" /></td>
                   <td><c:out value="${usuario.email}" /></td>
                   <td>
                       <c:choose>
                           <c:when test="${usuario.empleado}">
                               <span class="stock stock-ok">Empleado</span>
                           </c:when>
                           <c:otherwise>
                               <span class="stock">Cliente</span>
                           </c:otherwise>
                       </c:choose>
                   </td>
                   <td class="actions">
                       <a href="UsuarioController?accion=show&id=${usuario.ID}" class="btn btn-ver">Ver</a>
                       <a href="UsuarioController?accion=edit&id=${usuario.ID}" class="btn btn-editar">Editar</a>
                   </td>
               </tr>
           </c:forEach>
       </tbody>
   </table>
   <hr>
   <div class="botonera">
       <a href=".." class="btn left">Atras</a>
       <a href="UsuarioController?accion=create" class="btn btn-add">+ Añadir Usuario</a>
   </div>
</div>

<%@ include file="/views/layout/footer.jsp" %>