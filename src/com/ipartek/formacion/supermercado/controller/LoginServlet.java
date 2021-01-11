package com.ipartek.formacion.supermercado.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.supermercado.accesodatos.DaoUser;
import com.ipartek.formacion.supermercado.modelo.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		DaoUser dao = Configuracion.daoUser;
		User usuario = dao.getByEmail(email);

		if (usuario != null && usuario.getPassword().equals(password)) {
			request.getSession().setAttribute("usuario", usuario);
			response.sendRedirect(request.getContextPath() + "/admin/index");
		} else {
			request.setAttribute("alertaTexto", "El usuario o la contraseña son incorrectos");
			request.setAttribute("alertaNivel", "danger");

			request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
		}
	}

}
