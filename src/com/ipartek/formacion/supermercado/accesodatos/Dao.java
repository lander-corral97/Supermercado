package com.ipartek.formacion.supermercado.accesodatos;

public interface Dao<T> {

	Iterable<T> getAll();

	T get(Long id);

	void insert(T object);

	void update(T object);

	void delete(Long id);

}
