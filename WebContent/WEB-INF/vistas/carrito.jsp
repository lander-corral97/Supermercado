<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

<h1>Carrito de la compra</h1>

<table class="table table-striped table-bordered table-hover">
	<thead class="thead-dark">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">Nombre</th>
			<th scope="col">Precio</th>
			<th scope="col">Cantidad</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${ carrito }" var="prod">
			<tr>
				<th scope="row">${ prod.value.id }</th>
				<td>${ prod.value.name }</td>
				<td>${ prod.value.discountedPrice }</td>
				<td>${ prod.value.quantity }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>