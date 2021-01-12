package com.ipartek.formacion.supermercado.accesodatos;

import com.ipartek.formacion.supermercado.exception.AccesoDatosException;

public interface Dao<T> {

	default Iterable<T> getAll() {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	};

	default T get(Long id) {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	};

	default void insert(T object) {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	};

	default void update(T object) {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	};

	default void delete(Long id) {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	};

}
