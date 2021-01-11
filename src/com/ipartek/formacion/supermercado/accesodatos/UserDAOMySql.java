package com.ipartek.formacion.supermercado.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.supermercado.exception.AccesoDatosException;
import com.ipartek.formacion.supermercado.modelo.User;

public class UserDAOMySql implements DaoUser {

	private final String URL = "jdbc:mysql://localhost:3306/supermercado?serverTimezone=UTC";
	private final String USER = "root";
	private final String PASS = "";

	private static final String SQL_SELECT = "Select * From usuarios";
	private static final String SQL_SELECT_ID = "Select * From usuarios Where Id=?";
	private static final String SQL_SELECT_EMAIL = "Select * From usuarios Where email=?";
	private static final String SQL_INSERT = "Insert Into usuarios (email,password) Values (?,?)";
	private static final String SQL_UPDATE = "Update usuarios SET email=?, password=? Where id=?";
	private static final String SQL_DELETE = "Delete From usuarios Where id = ?";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver de JDBC para MySql", e);
		}
	}

	private UserDAOMySql() {

	}

	private final static UserDAOMySql INSTANCE = new UserDAOMySql();

	public static UserDAOMySql getInstance() {
		return INSTANCE;
	}

	@Override
	public Iterable<User> getAll() {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(SQL_SELECT)) {
			ArrayList<User> users = new ArrayList<>();
			User u;
			while (rs.next()) {
				u = new User(rs.getLong("id"), rs.getString("email"), rs.getString("password"));

				users.add(u);
			}
			return users;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido conectar", e);
		}
	}

	@Override
	public User get(Long id) {

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {
			ps.setLong(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				User u = null;

				if (rs.next()) {
					u = new User(rs.getLong("id"), rs.getString("email"), rs.getString("password"));
				}

				return u;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido conectar", e);
		}
	}

	@Override
	public void insert(User user) {

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_INSERT);) {
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());

			int numeroRegistrosInsertados = ps.executeUpdate();

			if (numeroRegistrosInsertados != 1) {
				throw new AccesoDatosException("Registros Insertados: " + numeroRegistrosInsertados, new Exception());
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido conectar", e);
		}
	}

	@Override
	public void update(User user) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_UPDATE);) {
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setLong(3, user.getId());

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

			if (numeroRegistrosEliminados != 0) {
				throw new AccesoDatosException("Registros Eliminados: " + numeroRegistrosEliminados, new Exception());
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido conectar", e);
		}
	}

	@Override
	public User getByEmail(String email) {

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_EMAIL);) {
			ps.setString(1, email);

			try (ResultSet rs = ps.executeQuery()) {
				User u = null;

				if (rs.next()) {
					u = new User(rs.getLong("id"), rs.getString("email"), rs.getString("password"));
				}

				return u;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido conectar", e);
		}
	}

}
