<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<form action="agregartodocarrito" method="post">
	<button class="btn btn-primary btn-block mb-3">Agregar todos al carrito</button>
	<div class="row row-cols-1 row-cols-md-3 row-cols-xl-5">
		<c:forEach items="${ products }" var="prod">
			<div class="col mb-4">
				<div class="card h-100">
					<c:if test="${ prod.discount != null }">
						<p
							class="badge badge-pill badge-danger position-absolute px-3 py-1 m-2">${ prod.discount }%</p>
					</c:if>
					<img class="card-img-top" src="${ prod.imageUrl }"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title">${ prod.name }</h5>
						<p class="card-text">${ prod.description }</p>
						<p class="card-text lead">
							<strong> <c:if test="${ prod.discount != null }">
									<del
										style="background: linear-gradient(to left top, transparent 47.75%, currentColor 49.5%, currentColor 50.5%, transparent 52.25%); text-decoration: none">
								</c:if> <fmt:formatNumber type="currency" minFractionDigits="2"
									maxFractionDigits="2" value="${ prod.price }"></fmt:formatNumber>
								<c:if test="${ prod.discount != null }">
									</del>
								</c:if> <c:if test="${ prod.discount != null }">
									<span class="pl-3 text-danger"><fmt:formatNumber
											type="currency" minFractionDigits="2" maxFractionDigits="2"
											value="${ prod.discountedPrice }"></fmt:formatNumber></span>
								</c:if>
							</strong>
						</p>
						<p class="card-text">
							<small class="text-muted"><fmt:formatNumber
									type="currency" minFractionDigits="2" maxFractionDigits="2"
									value="${ prod.unitPerMeasuring }"></fmt:formatNumber> / ${ prod.unitMeasuring }</small>
						</p>
						<p class="card-text">
						<div class="input-group mx-auto" style="width: 9rem">
							<div class="input-group-prepend">
								<button class="btn btn-secondary btn-minus" type="button">
									<i class="fas fa-minus"></i>
								</button>
							</div>
							<input type="number"
								class="form-control text-center font-weight-bold" placeholder=""
								aria-label="Cantidad de ${ prod.name } a comprar" value="0" name="${ prod.id }">

							<div class="input-group-append">
								<button class="btn btn-secondary btn-plus" type="button">
									<i class="fas fa-plus"></i>
								</button>
							</div>
						</div>
						</p>
					</div>
					<a href="#" class="btn btn-primary btn-block">Añadir al carrito</a>
					<div class="card-footer">
						<small class="text-muted">${ prod.quantity } en stock</small>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	
</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>