package com.ipartek.formacion.supermercado.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.supermercado.exception.AccesoDatosException;
import com.ipartek.formacion.supermercado.modelo.Department;

public class DepartmentDAOMySql implements Dao<Department> {

	private final String URL = "jdbc:mysql://localhost:3306/supermercado?serverTimezone=UTC";
	private final String USER = "root";
	private final String PASS = "";

	private static final String SQL_SELECT = "SELECT * FROM departamento";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver de JDBC para MySql", e);
		}
	}

	private DepartmentDAOMySql() {
	}

	private final static DepartmentDAOMySql INSTANCE = new DepartmentDAOMySql();

	public static DepartmentDAOMySql getInstance() {
		return INSTANCE;
	}

	@Override
	public Iterable<Department> getAll() {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(SQL_SELECT)) {
			ArrayList<Department> departments = new ArrayList<>();
			Department d;
			while (rs.next()) {
				d = new Department(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"));

				departments.add(d);
			}
			return departments;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido conectar", e);
		}
	}

}
