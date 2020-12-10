<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<base href="${ pageContext.request.contextPath }/" />
<meta charset="UTF-8">
<title>Supermercado</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="css/all.min.css">
</head>
<body>
	<header class="sticky-top">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#">Supermercado</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link"
						href="principal">Inicio <span class="sr-only">(current)</span></a>
					</li>
					<c:if test="${ sessionScope.usuario != null }">
						<li class="nav-item"><a class="nav-link" href="admin/index">Mantenimiento Productos</a></li>
					</c:if>
				</ul>
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="verCarrito">Ver
							Carrito</a></li>
					<c:choose>
						<c:when test="${ sessionScope.usuario == null }">
							<li class="nav-item"><a class="nav-link" href="login">Login</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="navbar-text">${ sessionScope.usuario.email }</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="logout">Cerrar
									Sesión</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</nav>
	</header>
	<main class="container-fluid pt-3">