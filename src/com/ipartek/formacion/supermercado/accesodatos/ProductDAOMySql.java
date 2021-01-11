package com.ipartek.formacion.supermercado.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.supermercado.exception.AccesoDatosException;
import com.ipartek.formacion.supermercado.modelo.Product;

public class ProductDAOMySql implements Dao<Product> {

	private final String URL = "jdbc:mysql://localhost:3306/supermercado?serverTimezone=UTC";
	private final String USER = "root";
	private final String PASS = "";

	private static final String SQL_SELECT = "Select * From productos";
	private static final String SQL_SELECT_ID = "Select * From productos Where Id=?";
	private static final String SQL_INSERT = "Insert Into productos (nombre,descripcion,url_imagen,precio,descuento,unidad_medida,precio_unidad_medida,cantidad) Values (?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "Update productos SET nombre=?, descripcion=?,url_imagen=?,precio=?,descuento=?,unidad_medida=?,precio_unidad_medida=?,cantidad=? Where id=?";
	private static final String SQL_DELETE = "Delete From productos Where id = ?";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver de JDBC para MySql", e);
		}
	}

	private ProductDAOMySql() {

	}

	private final static ProductDAOMySql INSTANCE = new ProductDAOMySql();

	public static ProductDAOMySql getInstance() {
		return INSTANCE;
	}

	@Override
	public Iterable<Product> getAll() {

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(SQL_SELECT)) {
			ArrayList<Product> products = new ArrayList<>();
			Product p;
			while (rs.next()) {
				p = new Product(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"),
						rs.getString("url_imagen"), rs.getBigDecimal("precio"), rs.getInt("descuento"),
						rs.getString("unidad_medida"), rs.getBigDecimal("precio_unidad_medida"), rs.getInt("cantidad"));

				products.add(p);
			}
			return products;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido conectar", e);
		}
	}

	@Override
	public Product get(Long id) {

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {
			ps.setLong(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				Product p = null;

				if (rs.next()) {
					p = new Product(rs.getLong("id"), rs.getString("nombre"), rs.getString("descuento"),
							rs.getString("url_imagen"), rs.getBigDecimal("precio"), rs.getInt("descuento"),
							rs.getString("unidad_medida"), rs.getBigDecimal("precio_unidad_medida"),
							rs.getInt("cantidad"));

				}

				return p;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido conectar", e);
		}
	}

	@Override
	public void insert(Product product) {

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_INSERT);) {
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setString(3, product.getImageUrl());
			ps.setBigDecimal(4, product.getPrice());
			ps.setInt(5, product.getDiscount());
			ps.setString(6, product.getUnitMeasuring());
			ps.setBigDecimal(7, product.getUnitPerMeasuring());
			ps.setInt(8, product.getQuantity());

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

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_UPDATE);) {
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setString(3, product.getImageUrl());
			ps.setBigDecimal(4, product.getPrice());
			ps.setInt(5, product.getDiscount());
			ps.setString(6, product.getUnitMeasuring());
			ps.setBigDecimal(7, product.getUnitPerMeasuring());
			ps.setInt(8, product.getQuantity());
			ps.setLong(9, product.getId());

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

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_DELETE);) {
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
