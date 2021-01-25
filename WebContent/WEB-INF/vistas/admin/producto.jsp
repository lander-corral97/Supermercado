<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>
<form action="" method="post" class="needs-validation my-3" novalidate enctype="multipart/form-data">
	
	<div class="form-group row">
		<label for="id" class="col-sm-2 col-form-label">Id</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" readonly id="id" name="id" value="${ producto.id }">
			<div class="valid-feedback">ID Correcto</div>
			<div class="invalid-feedback"></div>
		</div>
	</div>
	
	<div class="form-group row">
		<label for="departamento" class="col-sm-2 col-form-label">Departamento</label>
		<div class="col-sm-10">
			<select class="form-control ${ producto.errorDepartment != null ? 'is-invalid' : ''}" id="departamento" name="departamento"
			onchange="if(this.value == -1) { $('.departamento').fadeIn('slow') } else { $('.departamento').fadeOut('slow') }">
				<option value="0">Introduzca el departamento</option>
				<c:forEach items="${ departamentos }" var="departamento">
					<option value="${ departamento.id }" ${ departamento.id == producto.department.id ? 'selected' : '' }>${ departamento.name }</option>
				</c:forEach>
				<option disabled="disabled">⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯</option>
				<option value="-1">Agregar nuevo departamento</option>	
			</select>
		</div>
		<div class="valid-feedback">Departamento Correcto</div>
		<div class="invalid-feedback">${ producto.errorDepartment }</div>
	</div>
	
	<div class="form-group row departamento" style="display:none">
		<label for="departamento-nombre" class="col-sm-2 col-form-label">Nombre de Departamento</label>
		
		<div class="col-sm-10">
			<input type="text" class="form-control" id="departamento-descripcion" name="departamento-descripcion" 
				minlength="3" pattern="[A-Z][a-z]*" placeholder="Introduzca el nombre del departamento">
			<div class="invalid-feedback">Debe introducir un nombre con
				como mínimo 3 letras, y tan sólo letras y mayúscula la primera.</div>
		</div>
	</div>
	
	<div class="form-group row departamento" style="display:none">
		<label for="departamento-descripcion" class="col-sm-2 col-form-label">Descripcion de Departamento</label>
		<div class="col-sm-10">
			<textarea class="form-control" id="departamento-nombre" name="departamento-nombre"></textarea>
		</div>
	</div>
	
	<div class="form-group row">
		<label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="nombre" name="nombre" required
				minlength="3" pattern="[A-Z][a-z]*" value="${ producto.name }">
			<div class="invalid-feedback">Debe introducir un nombre con
				como mínimo 3 letras, y tan sólo letras y mayúscula la primera.</div>
		</div>
	</div>
	
	<div class="form-group row">
		<label for="imagen" class="col-sm-2 col-form-label">Imagen</label>
		<div class="col-sm-10">
			<div class="custom-file">
				<input type="file" class="custom-file-input" id="imagen" lang="es" name="imagen" value="${ producto.imageUrl }">
				<label class="custom-file-label" for="imagen" data-browse="Elegir">${ producto.imageUrl != null ? producto.imageUrl : 'Seleccionar Archivo'}</label>
				<button class="btn btn-danger" type="button" onclick="$('.custom-file [for=imagen]').text('');$('[name=imagenAnterior]').val('')">Borrar Imagen</button>
			</div>
			<div class="valid-feedback">Imagen Correcta</div>
			<div class="invalid-feedback"></div>
		</div>
	</div>
	
	<input type="hidden" name="imagenAnterior" value="${ producto.imageUrl }" />
	
	<div class="form-group row">
		<label for="descripcion" class="col-sm-2 col-form-label">Descripción</label>
		<div class="col-sm-10">
			<textarea class="form-control" id="descripcion" name="descripcion"
			placeholder="Descripción del producto">${ producto.description }</textarea>
			<div class="valid-feedback">Descripción Correcta</div>
			<div class="invalid-feedback"></div>
		</div>
	</div>
	
	<div class="form-group row">
		<label for="precio" class="col-sm-2 col-form-label">Precio</label>
		<div class="input-group col-sm-10">
			<input type="number" step=".01" class="form-control" id="precio" name="precio" min="0" required value="${ producto.price }">
			<div class="input-group-append">
				<span class="input-group-text rounded-right">€</span>
			</div>
			<div class="valid-feedback">Precio Correcto</div>
			<div class="invalid-feedback">Debe rellenarse y ser mayor que 0</div>
		</div>
	</div>
	
	<div class="form-group row">
		<label for="cantidad" class="col-sm-2 col-form-label">Cantidad</label>
		<div class="col-sm-10">
			<input type="number" class="form-control" id="cantidad" name="cantidad" min="0" required value="${ producto.quantity }">
			<div class="valid-feedback">Cantidad Correcta</div>
			<div class="invalid-feedback">Debe rellenarse</div>
		</div>
	</div>
	
	<div class="form-group row">
		<label for="unidad-medida" class="col-sm-2 col-form-label">Unidad de medida</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="unidad-medida" name="unidad-medida" value="${ producto.unitMeasuring }">
			<div class="valid-feedback">Unidad de medida</div>
			<div class="invalid-feedback"></div>
		</div>
	</div>
	
	<div class="form-group row">
		<label for="precio-unidad-medida" class="col-sm-2 col-form-label">Precio / Unidad</label>
		<div class="input-group col-sm-10">
			<input type="number" step=".01" class="form-control" id="precio-unidad-medida" name="precio-unidad-medida" min="0" value="${ producto.unitPerMeasuring }">
			<div class="input-group-append">
				<span class="input-group-text rounded-right">€</span>
			</div>
			<div class="valid-feedback">Precio / Medida Correcto</div>
			<div class="invalid-feedback">Debe ser mayor que 0</div>
		</div>
	</div>
	
	<div class="form-group row">
		<label for="descuento" class="col-sm-2 col-form-label">Descuento</label>
		<div class="col-sm-10">
			<input type="number" class="form-control" id="descuento" name="descuento" min="0" max="100" value="${ producto.discount }">
			<div class="valid-feedback">Descuento Correcto</div>
			<div class="invalid-feedback">Debe ser mayor que 0 y menor que 100</div>
		</div>
	</div>
	
	<button class="btn btn-primary">Aceptar</button>
</form>
<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>