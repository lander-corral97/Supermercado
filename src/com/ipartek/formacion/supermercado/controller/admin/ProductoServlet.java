package com.ipartek.formacion.supermercado.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ipartek.formacion.supermercado.accesodatos.Dao;
import com.ipartek.formacion.supermercado.controller.Configuracion;
import com.ipartek.formacion.supermercado.modelo.Department;
import com.ipartek.formacion.supermercado.modelo.Product;

@WebServlet("/admin/producto")
@MultipartConfig
public class ProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ProductoServlet.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		if (id != null) {
			Dao<Product> daoProd = Configuracion.daoProduct;
			Product prod = daoProd.get(Long.parseLong(id));

			request.setAttribute("producto", prod);
		}

		Dao<Department> daoDept = Configuracion.daoDepartment;

		Iterable<Department> departments = daoDept.getAll();

		request.setAttribute("departamentos", departments);

		request.getRequestDispatcher("/WEB-INF/vistas/admin/producto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Cambiar codificación de entrada de datos de Windows-1253 a UTF8

		request.setCharacterEncoding("utf-8");

		// Recoger los datos

		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String urlImagen = request.getParameter("imagen");
		String urlImagenAnterior = request.getParameter("imagenAnterior");
		String descripcion = request.getParameter("descripcion");
		String precio = request.getParameter("precio");
		String cantidad = request.getParameter("cantidad");
		String unidadMedida = request.getParameter("unidad-medida");
		String precioUnidadMedida = request.getParameter("precio-unidad-medida");
		String descuento = request.getParameter("descuento");
		String departamentoId = request.getParameter("departamento");

		// Comprobar que existe el lugar para guardar las imagen

		String rutaSubida = request.getServletContext().getRealPath("") + File.separator + "images";
		File dirSubida = new File(rutaSubida);
		if (!dirSubida.exists()) {
			dirSubida.mkdir();
		}

		// Crear el fichero y darle a la url un valor.

		Part part = request.getPart("imagen");
		String nombreFichero = getNombreFichero(part);
		if (!nombreFichero.equals("")) {
			part.write(rutaSubida + File.separator + nombreFichero);
			urlImagen = "images/" + nombreFichero;
		}

		if (urlImagen == null) {
			urlImagen = urlImagenAnterior;
		}

		// Crear un producto e introducirlo / actualizarlo

		Product prod = new Product(id, nombre, descripcion, urlImagen, precio, descuento, unidadMedida,
				precioUnidadMedida, cantidad);

		prod.setDepartment(new Department(Long.parseLong(departamentoId), null, null));

		LOGGER.log(Level.INFO, prod.toString());

		Dao<Product> dao = Configuracion.daoProduct;

		if (!prod.isCorrecto()) {
			/*
			 * if (prod.getId() != null && urlImagen == null) {
			 * prod.setImageUrl(dao.get(prod.getId()).getImageUrl()); }
			 */

			request.setAttribute("producto", prod);

			Iterable<Department> departamentos = Configuracion.daoDepartment.getAll();

			request.setAttribute("departamentos", departamentos);

			request.getRequestDispatcher("/WEB-INF/vistas/admin/producto.jsp").forward(request, response);
			return;
		}

		String mensaje = "";

		if (prod.getId() == null) {
			dao.insert(prod);
			mensaje = "Se ha creado el producto correctamente";
		} else {
			/*
			 * if (urlImagen == null) {
			 * prod.setImageUrl(dao.get(prod.getId()).getImageUrl()); }
			 */
			dao.update(prod);
			mensaje = "Se ha creado el producto correctamente";
		}
		// Añadir mensaje

		request.setAttribute("alertaTexto", mensaje);
		request.setAttribute("alertaNivel", "success");
		// Redirigir a la página deseada

		request.getRequestDispatcher("/admin/index").forward(request, response);
	}

	private String getNombreFichero(Part part) {
		for (String contenido : part.getHeader("content-disposition").split(";")) {
			if (contenido.trim().startsWith("filename"))
				return contenido.substring(contenido.indexOf("=") + 2, contenido.length() - 1);
		}
		return "";
	}

}
