package com.ipartek.formacion.supermercado.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ipartek.formacion.supermercado.exception.AccesoDatosException;
import com.ipartek.formacion.supermercado.modelo.Department;
import com.ipartek.formacion.supermercado.modelo.Product;

public class ProductDAOMySql implements Dao<Product> {

	private static final String SQL_SELECT = "Select * From productos p Join departamento d On p.departamento_id = d.id";
	private static final String SQL_SELECT_ID = "Select * From productos p Join departamento d On p.departamento_id = d.id Where p.Id=?";
	private static final String SQL_INSERT = "Insert Into productos (nombre,descripcion,url_imagen,precio,descuento,unidad_medida,precio_unidad_medida,cantidad,departamento_id) Values (?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "Update productos SET nombre=?, descripcion=?,url_imagen=?,precio=?,descuento=?,unidad_medida=?,precio_unidad_medida=?,cantidad=?,departamento_id=? Where id=?";
	private static final String SQL_DELETE = "Delete From productos Where id = ?";

	private DataSource dataSource;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver de JDBC para MySql", e);
		}
	}

	private ProductDAOMySql() {
		try {
			InitialContext initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			dataSource = (DataSource) envCtx.lookup("jdbc/supermercado");
		} catch (NamingException e) {
			throw new AccesoDatosException("No se ha encontrado el JNDI de supermercado", e);
		}
	}

	private final static ProductDAOMySql INSTANCE = new ProductDAOMySql();

	public static ProductDAOMySql getInstance() {
		return INSTANCE;
	}

	private Connection obtenerConexion() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un error al obtener la conexión", e);
		}
	}

	@Override
	public Iterable<Product> getAll() {

		try (Connection con = obtenerConexion();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(SQL_SELECT)) {
			ArrayList<Product> products = new ArrayList<>();
			Product p;
			Department d;
			while (rs.next()) {
				p = new Product(rs.getLong("p.id"), rs.getString("nombre"), rs.getString("p.descripcion"),
						rs.getString("p.url_imagen"), rs.getBigDecimal("p.precio"), rs.getInt("p.descuento"),
						rs.getString("p.unidad_medida"), rs.getBigDecimal("p.precio_unidad_medida"),
						rs.getInt("p.cantidad"));

				d = new Department(rs.getLong("d.id"), rs.getString("d.nombre"), rs.getString("d.descripcion"));

				p.setDepartment(d);

				products.add(p);
			}
			return products;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido conectar", e);
		}
	}

	@Override
	public Product get(Long id) {

		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {
			ps.setLong(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				Product p = null;
				Department d = null;
				if (rs.next()) {
					p = new Product(rs.getLong("p.id"), rs.getString("p.nombre"), rs.getString("p.descripcion"),
							rs.getString("p.url_imagen"), rs.getBigDecimal("p.precio"), rs.getInt("p.descuento"),
							rs.getString("p.unidad_medida"), rs.getBigDecimal("p.precio_unidad_medida"),
							rs.getInt("p.cantidad"));

					d = new Department(rs.getLong("d.id"), rs.getString("d.nombre"), rs.getString("d.descripcion"));

					p.setDepartment(d);
				}

				return p;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido conectar", e);
		}
	}

	@Override
	public void insert(Product product) {

		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_INSERT);) {
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setString(3, product.getImageUrl());
			ps.setBigDecimal(4, product.getPrice());
			ps.setInt(5, product.getDiscount());
			ps.setString(6, product.getUnitMeasuring());
			ps.setBigDecimal(7, product.getUnitPerMeasuring());
			ps.setInt(8, product.getQuantity());
			ps.setLong(9, product.getDepartment().getId());

			int numeroRegistrosInsertados = ps.executeUpdate();

			if (numeroRegistrosInsertados != 1) {
				throw new AccesoDatosException("Registros Insertados: " + numeroRegistrosInsertados, new Exception());
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido conectar", e);
		}
	}

	@Override
	public void update(Product product) {

		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE);) {
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setString(3, product.getImageUrl());
			ps.setBigDecimal(4, product.getPrice());
			ps.setInt(5, product.getDiscount());
			ps.setString(6, product.getUnitMeasuring());
			ps.setBigDecimal(7, product.getUnitPerMeasuring());
			ps.setInt(8, product.getQuantity());
			ps.setLong(9, product.getDepartment().getId());
			ps.setLong(10, product.getId());

			int numeroRegistrosModificados = ps.executeUpdate();

			if (numeroRegistrosModificados != 1) {
				throw new AccesoDatosException("Registros Modificados: " + numeroRegistrosModificados, new Exception());
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido conectar", e);
		}
	}

	@Override
	public void delete(Long id) {

		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_DELETE);) {
			ps.setLong(1, id);

			int numeroRegistrosEliminados = ps.executeUpdate();

			if (numeroRegistrosEliminados != 1) {
				throw new AccesoDatosException("Registros Eliminados: " + numeroRegistrosEliminados, new Exception());
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido conectar", e);
		}
	}

}
