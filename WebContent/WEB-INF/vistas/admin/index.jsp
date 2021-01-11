<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="table-responsive">
	<table class="table table-striped table-bordered table-hover table-sm">
		<caption></caption>
		<thead class="thead-dark">
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Nombre</th>
				<th scope="col">Imagen</th>
				<th scope="col">Descripción</th>
				<th scope="col">Precio</th>
				<th scope="col">Cantidad</th>
				<th scope="col">Unidad de Medida</th>
				<th scope="col">Precio por unidad</th>
				<th scope="col">Descuento</th>
				<th scope="col">Opciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ productos }" var="prod">
				<tr>
					<th scope="row">${ prod.id }</th>
					<td>${ prod.name }</td>
					<td><img src="${ prod.imageUrl }" style="height: 3em"/></td>
					<td>${ prod.description }</td>
					<td><fmt:formatNumber type="currency" value="${ prod.price }"/></td>
					<td>${ prod.quantity }</td>
					<td>${ prod.unitMeasuring }</td>
					<td><fmt:formatNumber type="currency" value="${ prod.unitPerMeasuring }" /></td>
					<td><fmt:formatNumber type="percent" value="${ prod.discount / 100 }"/></td>
                    <td>
                        <div class="btn-group" role="group" aria-label="Basic example">
                            <a class="btn btn-primary btn-sm" href="admin/producto?id=${ prod.id }">Editar</a>
                            <a onclick="return confirm('¿Estás seguro?')" class="btn btn-danger btn-sm" href="admin/borrar?id=${ prod.id }">Borrar</a>
                        </div>
                    </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a class="btn btn-primary" href="admin/producto">Añadir Producto</a>
	</p>
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>