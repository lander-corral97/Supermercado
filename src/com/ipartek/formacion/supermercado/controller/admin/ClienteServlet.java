package com.ipartek.formacion.supermercado.controller.admin;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.supermercado.modelo.Client;

@WebServlet("/admin/cliente")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger LOGGER = Logger.getLogger(ClienteServlet.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/vistas/admin/cliente.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String firstName = request.getParameter("nombre");
		String lastName = request.getParameter("apellidos");
		String cif = request.getParameter("cif");
		String birthDate = request.getParameter("fecha-nacimiento");

		Client cliente = new Client(id, firstName, lastName, cif, birthDate);

		LOGGER.info(cliente.toString());

		if (cliente.isCorrecto()) {
			response.sendRedirect(request.getContextPath() + "/admin/index");
		} else {
			request.setAttribute("cliente", cliente);
			request.getRequestDispatcher("/WEB-INF/vistas/admin/cliente.jsp").forward(request, response);
		}
	}

}
