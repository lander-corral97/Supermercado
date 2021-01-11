package com.ipartek.formacion.supermercado.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.supermercado.accesodatos.Dao;
import com.ipartek.formacion.supermercado.modelo.Product;

@WebServlet("/agregartodocarrito")
public class AgregarTodoCarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Dao<Product> dao = Configuracion.daoProduct;

		LinkedHashMap<Long, Product> shoppingCart = new LinkedHashMap<>();

		Enumeration<String> ids = request.getParameterNames();

		String sId;
		Long id;
		Integer quantity;

		Product prod;

		while (ids.hasMoreElements()) {
			sId = ids.nextElement();
			id = Long.parseLong(sId);
			quantity = Integer.parseInt(request.getParameter(sId));

			if (quantity > 0) {
				prod = dao.get(id);
				prod.setQuantity(quantity);

				shoppingCart.put(id, prod);
			}
		}
		request.getSession().setAttribute("carrito", shoppingCart);

		response.sendRedirect(request.getContextPath() + "/carrito");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
