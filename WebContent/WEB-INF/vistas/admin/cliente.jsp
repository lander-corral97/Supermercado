<%@ page import="java.time.LocalDate" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<form action="admin/cliente" method="post" class="needs-validation"
	novalidate>
	<div class="form-group row">
		<label for="id" class="col-md-5 col-lg-4 col-xl-3 col-form-label">Id (Opcional)</label>
		<div class="col">
			<input class="form-control ${ cliente != null ? (cliente.errorId != null ? 'is-invalid' : 'is-valid') : '' }" type="number" name="id" placeholder="Id"
				min="1">
			<div class="valid-feedback">Id correcto</div>
			<div class="invalid-feedback">El id debe ser superior o igual a
				1</div>
		</div>
	</div>

	<div class="form-group row">
		<label for="nombre" class="col-md-5 col-lg-4 col-xl-3 col-form-label">Nombre</label>
		<div class="col">
			<input class="form-control ${ cliente != null ? (cliente.errorFirstName != null ? 'is-invalid' : 'is-valid') : '' }" type="text" name="nombre"
				placeholder="Nombre" required pattern="\p{Lu}\p{Ll}{2}[ \p{Ll}]*">
			<div class="valid-feedback">Nombre correcto</div>
			<div class="invalid-feedback">El nombre es obligatorio y debe
				tener al menos 3 caracteres y empezar por mayúscula</div>
		</div>
	</div>

	<div class="form-group row">
		<label for="apellidos" class="col-md-5 col-lg-4 col-xl-3 col-form-label">Apellidos
			(Opcional)</label>
		<div class="col">
			<input class="form-control ${ cliente != null ? (cliente.errorLastName != null ? 'is-invalid' : 'is-valid') : '' }" type="text" name="apellidos"
				placeholder="Apellidos" pattern="\p{Lu}\p{Ll}{2}[ \p{Ll}]*">
			<div class="valid-feedback">Apellidos correctos</div>
			<div class="invalid-feedback">El apellido debe tener al menos
				tres caracteres y empezar por mayúscula</div>
		</div>
	</div>

	<div class="form-group row">
		<label for="cif" class="col-md-5 col-lg-4 col-xl-3 col-form-label">CIF</label>
		<div class="col">
			<input class="form-control ${ cliente != null ? (cliente.errorCif != null ? 'is-invalid' : 'is-valid') : '' }" type="text" name="cif" placeholder="Cif"
				required
				pattern="[ABCDEFGHJPQRSUVNW]\d{8}|[XYZ]\d{7}[A-Z]|\d{8}[A-Z]">
			<div class="valid-feedback">CIF correcto</div>
			<div class="invalid-feedback">El CIF debe tener un formato
				12345678A, B12345678 ó X1234567A</div>
		</div>
	</div>

	<div class="form-group row">
		<label for="fecha-nacimiento" class="col-md-5 col-lg-4 col-xl-3 col-form-label">Fecha
			de nacimiento (opcional)</label>
		<div class="col">
			<input class="form-control ${ cliente != null ? (cliente.errorBirthDate != null ? 'is-invalid' : 'is-valid') : '' }" type="date" name="fecha-nacimiento"
				placeholder="Fecha de Nacimiento"
				max="<%=LocalDate.now().minusYears(18)%>">
			<div class="valid-feedback">Fecha correcta</div>
			<div class="invalid-feedback">Debes ser mayor de edad para
				registrarte</div>
		</div>
	</div>

	<div class="form-group row">
		<div class="offset-md-5 offset-lg-4 offset-xl-3 col">
			<button type="submit" class="btn btn-primary">Aceptar</button>
		</div>
	</div>


	<div class="text-danger">${ cliente.errorId }</div>
	<div class="text-danger">${ cliente.errorFirstName }</div>
	<div class="text-danger">${ cliente.errorLastName }</div>
	<div class="text-danger">${ cliente.errorCif }</div>
	<div class="text-danger">${ cliente.errorBirthDate }</div>
</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>